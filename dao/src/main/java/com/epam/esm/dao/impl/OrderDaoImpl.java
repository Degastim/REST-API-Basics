package com.epam.esm.dao.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.constant.sql.OrderSql;
import com.epam.esm.dto.PaginationContainer;
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
    public List<Order> findAllByUserId(long userId, PaginationContainer paginationContainer) {
        Session session = sessionFactory.openSession();
        int page = paginationContainer.getPage();
        int size = paginationContainer.getSize();
        int previousPageEnd = (page - 1) * size;
        List<Order> list;
        if (size == 0 && page == 0) {
            list = session.createSQLQuery(OrderSql.FIND_ALL_BY_USER_ID).addEntity(Order.class)
                    .setParameter(1, userId).list();
        } else {
            list = session.createSQLQuery(OrderSql.FIND_ALL_BY_USER_ID_AND_LIMIT).addEntity(Order.class)
                    .setParameter(1, userId).setParameter(2, previousPageEnd).setParameter(3, size).list();
        }
        return list;
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
    }
}
