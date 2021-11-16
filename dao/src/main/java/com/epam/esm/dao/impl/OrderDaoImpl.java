package com.epam.esm.dao.impl;

import com.epam.esm.dao.OrderDao;
import com.epam.esm.dao.constant.sql.OrderSql;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.entity.Order;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * OrderDao implementation.
 *
 * @author Yauheni Tsitov
 */
@Repository
public class OrderDaoImpl implements OrderDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Order> findAllByUserId(long userId, PaginationContainer paginationContainer) {
        int page = paginationContainer.getPage();
        int size = paginationContainer.getSize();
        int previousPageEnd = (page - 1) * size;
        List<Order> list;
        if (size == 0 && page == 0) {
            list = entityManager.createNativeQuery(OrderSql.FIND_ALL_BY_USER_ID, Order.class)
                    .setParameter(1, userId).getResultList();
        } else {
            list = entityManager.createNativeQuery(OrderSql.FIND_ALL_BY_USER_ID_AND_LIMIT, Order.class)
                    .setParameter(1, userId).setParameter(2, previousPageEnd).setParameter(3, size).getResultList();
        }
        return list;
    }

    @Override
    public Optional<Order> findByUserIdAndOrderId(long userId, long orderId) {
        List<Order> orderList = entityManager.createNativeQuery(OrderSql.FIND_ALL_BY_USER_ID_AND_ORDER_ID, Order.class)
                .setParameter(1, userId).setParameter(2, orderId).getResultList();
        return returnOrder(orderList);
    }

    @Override
    public void add(Order order) {
        entityManager.persist(order);
    }

    private Optional<Order> returnOrder(List<Order> orderList) {
        if (orderList.size() != 0) {
            return Optional.of(orderList.get(0));
        } else {
            return Optional.empty();
        }
    }
}
