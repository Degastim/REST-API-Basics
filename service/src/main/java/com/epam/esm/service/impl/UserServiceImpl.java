package com.epam.esm.service.impl;

import com.epam.esm.creator.UserDetailsConverter;
import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.UserCredential;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.entity.user.User;
import com.epam.esm.entity.user.UserRole;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.mapper.UserCredentialMapper;
import com.epam.esm.mapper.UserDTOMapper;
import com.epam.esm.service.UserService;
import com.epam.esm.validator.PaginationContainerValidator;
import com.epam.esm.validator.UserCredentialValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserDTOMapper userDTOMapper;
    private final PaginationContainerValidator paginationContainerValidator;
    private final UserDetailsConverter userDetailsConverter;
    private final UserCredentialMapper userCredentialMapper;
    private final UserCredentialValidator userCredentialValidator;
    private final PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserDTOMapper userDTOMapper, UserCredentialMapper userCredentialMapper,
                           PaginationContainerValidator paginationContainerValidator, UserCredentialValidator userCredentialValidator,
                           UserDetailsConverter userDetailsConverter, PasswordEncoder encoder) {
        this.userDao = userDao;
        this.userDTOMapper = userDTOMapper;
        this.paginationContainerValidator = paginationContainerValidator;
        this.userDetailsConverter = userDetailsConverter;
        this.userCredentialMapper = userCredentialMapper;
        this.userCredentialValidator = userCredentialValidator;
        this.encoder = encoder;
    }

    @Override
    public List<UserDTO> finaAll(PaginationContainer paginationContainer) {
        paginationContainerValidator.paginationPaginationContainer(paginationContainer);
        List<User> userList = userDao.findAll(paginationContainer);
        return userList.stream().map(userDTOMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(long id) {
        User user = userDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundedException("User with this ID not found", ExceptionCauseCode.USER));
        return userDTOMapper.toDTO(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.findByName(username).orElseThrow(
                () -> new ResourceNotFoundedException("User with this username not found", ExceptionCauseCode.USER));
        return userDetailsConverter.convert(user);
    }

    @Override
    public UserDTO save(UserCredential userCredential) {
        userCredentialValidator.isUserValid(userCredential);
        User user = userCredentialMapper.toEntity(userCredential);
        user.setRole(UserRole.USER);
        user.setActive(true);
        user.setPassword(encoder.encode(user.getPassword()));
        userDao.save(user);
        return userDTOMapper.toDTO(user);
    }

    @Override
    public UserDTO findByName(String name) {
        User user = userDao.findByName(name).orElseThrow(() ->
                new ResourceNotFoundedException("No such user was found.", ExceptionCauseCode.USER));
        return userDTOMapper.toDTO(user);
    }
}
