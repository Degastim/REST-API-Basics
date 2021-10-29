package com.epam.esm.service;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.entity.Order;

import java.util.List;

public interface OrderService {
    List<OrderDTO> findAllByUserId(long userId, PaginationContainer paginationContainer);
    OrderDTO findById(long orderId);
    OrderDTO addOrder(long userId,OrderDTO orderDTO);
}
