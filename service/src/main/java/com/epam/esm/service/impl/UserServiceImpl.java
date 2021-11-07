package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.entity.User;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.mapper.UserDTOMapper;
import com.epam.esm.service.UserService;
import com.epam.esm.validator.PaginationContainerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserDTOMapper userDTOMapper;
    private final PaginationContainerValidator paginationContainerValidator;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserDTOMapper userDTOMapper, PaginationContainerValidator paginationContainerValidator) {
        this.userDao = userDao;
        this.userDTOMapper = userDTOMapper;
        this.paginationContainerValidator = paginationContainerValidator;
    }

    @Override
    public List<UserDTO> finaAll(PaginationContainer paginationContainer) {
        paginationContainerValidator.isPaginationContainerValid(paginationContainer);
        List<User> userList = userDao.findAll(paginationContainer);
        return userList.stream().map(userDTOMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(long id) {
        User user = userDao.findById(id).orElseThrow(
                () -> new ResourceNotFoundedException("User with this ID not found", ExceptionCauseCode.USER));
        return userDTOMapper.toDTO(user);
    }
}
