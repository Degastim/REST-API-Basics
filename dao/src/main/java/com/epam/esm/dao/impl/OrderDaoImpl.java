package com.epam.esm.dao.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.entity.Order;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class OrderDaoImpl implements OrderDao {
    private final SessionFactory sessionFactory;

    @Autowired
    public OrderDaoImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public List<Order> findAllByUserId(long userId) {
        return (List<Order>) sessionFactory.openSession().createSQLQuery("Select * FROM orders WHERE user_id=?").setParameter(1, userId).addEntity(Order.class).list();
    }

    @Override
    public Optional<Order> findById(long orderId) {
        Order order = sessionFactory.openSession().find(Order.class, orderId);
        return Optional.ofNullable(order);
    }

    @Override
    public void add(Order order) {
        Long id = (Long) sessionFactory.openSession().save(order);
        order.setId(id);
    }
}
