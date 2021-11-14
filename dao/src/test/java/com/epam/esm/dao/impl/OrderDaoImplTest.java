package com.epam.esm.dao.impl;

import com.epam.esm.Application;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertSame;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        classes = Application.class)
class OrderDaoImplTest {
    @Autowired
    private OrderDao orderDao;

    @Test
    void findAllByUserId() {
        long userId = 1;
        int expected = 2;
        List<Order> orders = orderDao.findAllByUserId(userId, new PaginationContainer(0, 0));
        assertSame(expected, orders.size());
    }

    @Test
    void findById() {
        long id = 1;
        Optional<Order> orderOptional = orderDao.findById(id);
        assertDoesNotThrow(orderOptional::get);
    }

    @Test
    void add() {
        Order order = new Order();
        order.setGiftCertificate(new GiftCertificate(1));
        order.setCreateDate(LocalDateTime.now());
        order.setUser(new User(1));
        assertDoesNotThrow(() -> orderDao.add(order));
    }
}