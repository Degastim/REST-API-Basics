package com.epam.esm.service;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.PaginationContainer;

import java.util.List;

/**
 * Interface provides actions on order.
 *
 * @author Yauheni Tstiov
 */
public interface OrderService {
    /**
     * Searches all user orders by his id.
     *
     * @param userId              orders to be found.
     * @param paginationContainer contains pagination options.
     * @return list of the found orderDTO.
     */
    List<OrderDTO> findAllByUserId(long userId, PaginationContainer paginationContainer);

    /**
     * Looking for all orders by its id.
     *
     * @param userId
     * @param orderId order id to find.
     * @return found orderDTO.
     */
    OrderDTO findById(long userId, long orderId);

    /**
     * Adds an order.
     *
     * @param userId   id of the user who made the order.
     * @param orderDTO contains order data.
     * @return list of the add orderDTO.
     */
    OrderDTO addOrder(long userId, OrderDTO orderDTO);
}
