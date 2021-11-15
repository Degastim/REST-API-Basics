package com.epam.esm.service.impl;

import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.entity.user.User;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.mapper.UserDTOMapper;
import com.epam.esm.service.UserService;
import com.epam.esm.validator.PaginationContainerValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class UserServiceImplTest {
    @Mock
    private UserDao userDao;
    @Mock
    private UserDTOMapper userDTOMapper;
    @Mock
    private PaginationContainerValidator paginationContainerValidator;
    private UserService userService;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);

    }

    @Test
    void finaAll() {
        List<User> userList = new ArrayList<>();
        PaginationContainer paginationContainer = new PaginationContainer();
        List<UserDTO> actual = userService.finaAll(paginationContainer);
        List<UserDTO> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    void findById() {
        long id = 3;
        Mockito.when(userDao.findById(id)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundedException.class, () -> userService.findById(id));
    }
}