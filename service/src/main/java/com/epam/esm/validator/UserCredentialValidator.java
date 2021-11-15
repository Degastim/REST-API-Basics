package com.epam.esm.validator;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.UserCredential;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.InvalidFieldValueException;
import com.epam.esm.exception.ResourceAlreadyExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserCredentialValidator {
    private static final Pattern NAME_PATTERN = Pattern.compile("[a-zA-Zа-яА-Я]{1,40}");
    private static final Pattern PASSWORD_PATTERN = Pattern.compile("[а-яА-Я\\w\\s\\p{Punct}]{6,256}");
    private final UserDao userDao;

    @Autowired
    public UserCredentialValidator(UserDao userDao) {
        this.userDao = userDao;
    }

    public void isUserValid(UserCredential user) {
        StringBuilder errorMessage = new StringBuilder();
        String name = user.getName();
        if (!isNameValid(name)) {
            errorMessage.append("User name is not valid.");
        }
        if (!isPasswordValid(user.getPassword())) {
            errorMessage.append("Password is not valid.");
        }
        if (errorMessage.length() != 0) {
            throw new InvalidFieldValueException(errorMessage.toString(), ExceptionCauseCode.USER);
        }
        if (userDao.findByName(name).isPresent()) {
            throw new ResourceAlreadyExistException("There is already a user with the same name.", ExceptionCauseCode.USER);
        }
    }

    private boolean isNameValid(String name) {
        return (name != null && NAME_PATTERN.matcher(name).matches());
    }

    private boolean isPasswordValid(String password) {
        return (password != null && PASSWORD_PATTERN.matcher(password).matches());
    }
}