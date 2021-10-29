package com.epam.esm.service.impl;

import com.epam.esm.Paginator;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserDao userDao;
    private final UserDTOMapper userDTOMapper;
    private final PaginationContainerValidator paginationContainerValidator;
    private final Paginator<User> paginator;

    @Autowired
    public UserServiceImpl(UserDao userDao, UserDTOMapper userDTOMapper, PaginationContainerValidator paginationContainerValidator,
                           Paginator<User> paginator) {
        this.userDao = userDao;
        this.userDTOMapper = userDTOMapper;
        this.paginationContainerValidator = paginationContainerValidator;
        this.paginator = paginator;
    }

    @Override
    public List<UserDTO> finaAll(PaginationContainer paginationContainer) {
        paginationContainerValidator.isPaginationContainerValid(paginationContainer);
        List<User> userList = userDao.findAll();
        List<User> paginateList = paginator.paginate(userList, paginationContainer);
        return paginateList.stream().map(userDTOMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public UserDTO findById(long id) {
        Optional<User> optionalUser = userDao.findById(id);
        if (optionalUser.isPresent()) {
            return userDTOMapper.toDTO(optionalUser.get());
        } else {
            throw new ResourceNotFoundedException("User with this ID not found", ExceptionCauseCode.USER);
        }
    }
}
