package com.epam.esm.dao.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.constant.sql.OrderSql;
import com.epam.esm.entity.Order;
import org.hibernate.Session;
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
        return (List<Order>) sessionFactory.openSession().createSQLQuery(OrderSql.FIND_ALL_BY_USER_ID).setParameter(1, userId).addEntity(Order.class).list();
    }

    @Override
    public Optional<Order> findById(long orderId) {
        Session session = sessionFactory.openSession();
        Order order = session.find(Order.class, orderId);
        return Optional.ofNullable(order);
    }

    @Override
    public void add(Order order) {
        Session session = sessionFactory.getCurrentSession();
        session.persist(order);
        session.flush();
    }
}
