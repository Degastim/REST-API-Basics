package com.epam.esm.controller;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.hateoas.OrderDTOHateoas;
import com.epam.esm.service.OrderService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Orders controller class.
 *
 * @author Yauheni Tstiov
 */
@RestController
public class OrderController {
    private final OrderService orderService;
    private final OrderDTOHateoas orderDTOHateoas;

    public OrderController(OrderService orderService, OrderDTOHateoas orderDTOHateoas) {
        this.orderService = orderService;
        this.orderDTOHateoas = orderDTOHateoas;
    }

    /**
     * Find all orders by user ID.
     *
     * @param userId              user id whose orders we are looking for.
     * @param paginationContainer contains the desired page and the number of elements per page.
     * @return list of found users by HATEOAS
     */
    @GetMapping("/users/{userId}/orders")
    public CollectionModel<OrderDTO> findAllByUserId(@PathVariable long userId,
                                                     @ModelAttribute PaginationContainer paginationContainer) {
        List<OrderDTO> orderDTOList = orderService.findAllByUserId(userId, paginationContainer);
        return orderDTOHateoas.build(orderDTOList, paginationContainer, userId);
    }

    /**
     * Find order by ID.
     *
     * @param orderId order id by which we are looking in the database.
     * @return order found in the database by HATEOAS.
     */
    @GetMapping("/orders/{orderId}")
    public OrderDTO findByOrderId(@PathVariable long orderId) {
        OrderDTO result = orderService.findById(orderId);
        orderDTOHateoas.build(result);
        return result;
    }

    /**
     * Adds an order to the database.
     *
     * @param userId   id of the user who makes the order.
     * @param orderDTO contains an order to add to the database.
     * @return order added to the database by HATEOAS.
     */
    @PostMapping("/users/{userId}/orders")
    public OrderDTO addOrder(@PathVariable long userId, @RequestBody OrderDTO orderDTO) {
        OrderDTO result = orderService.addOrder(userId, orderDTO);
        orderDTOHateoas.build(result);
        return result;
    }
}
