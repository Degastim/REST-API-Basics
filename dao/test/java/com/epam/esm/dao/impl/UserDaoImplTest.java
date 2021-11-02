package com.epam.esm.dao.impl;

import com.epam.esm.Application;
import com.epam.esm.dao.UserDao;
import com.epam.esm.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = Application.class)
class UserDaoImplTest {
    @Autowired
    private UserDao userDao;

    @Test
    void findAll() {
        List<User> userList = userDao.findAll();
        assertEquals(userList.size(), 3);
    }

    @Test
    void findById() {
        long id = 1;
        Optional<User> userOptional = userDao.findById(id);
        User actual = userOptional.get();
        User expected = new User();
        expected.setId(id);
        expected.setName("Zhenya");
        actual.setOrderList(null);
        assertEquals(expected, actual);
    }
}