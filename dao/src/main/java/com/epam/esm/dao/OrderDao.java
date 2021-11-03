package com.epam.esm.dao;

import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.entity.Order;

import java.util.List;
import java.util.Optional;

/**
 * Interface used for interactions with orders table.
 *
 * @author Yauheni Tstiov
 */
public interface OrderDao {
    /**
     * Finds the order by user id.
     *
     * @param userId              id of the user who made the order.
     * @param paginationContainer contain information for pagination.
     * @return list which contains the found orders.
     */
    List<Order> findAllByUserId(long userId, PaginationContainer paginationContainer);

    /**
     * Finds the order by id.
     *
     * @param orderId of the object to be found.
     * @return optional which contains the found order.
     */
    Optional<Order> findById(long orderId);

    /**
     * Adds gift certificate to the database and add id to the certificate.
     *
     * @param order object to be added.
     */
    void add(Order order);
}
