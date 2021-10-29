package com.epam.esm.dao;

import com.epam.esm.entity.Order;

import java.util.List;
import java.util.Optional;

public interface OrderDao {
    List<Order> findAllByUserId(long userId);
    Optional<Order> findById(long orderId);
    void add(Order order);
}
