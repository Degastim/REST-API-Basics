package com.epam.esm.controller;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.hateoas.OrderDTOHateoas;
import com.epam.esm.service.OrderService;
import com.epam.esm.validator.OrderAccessValidator;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    private final OrderAccessValidator orderAccessValidator;

    public OrderController(OrderService orderService, OrderDTOHateoas orderDTOHateoas, OrderAccessValidator orderAccessValidator) {
        this.orderService = orderService;
        this.orderDTOHateoas = orderDTOHateoas;
        this.orderAccessValidator = orderAccessValidator;
    }

    /**
     * Find all orders by user ID.
     *
     * @param userId              user id whose orders we are looking for.
     * @param paginationContainer contains the desired page and the number of elements per page.
     * @return list of found users by HATEOAS
     */
    @GetMapping("/users/{userId}/orders")
    public CollectionModel<OrderDTO> findAllByUserId(HttpServletRequest httpServletRequest, @PathVariable long userId,
                                                     @ModelAttribute PaginationContainer paginationContainer) {
        orderAccessValidator.isUserValid(userId, httpServletRequest);
        List<OrderDTO> orderDTOList = orderService.findAllByUserId(userId, paginationContainer);
        return orderDTOHateoas.build(orderDTOList, paginationContainer, userId);
    }

    /**
     * Find order by ID.
     *
     * @param orderId order id by which we are looking in the database.
     * @return order found in the database by HATEOAS.
     */
    @GetMapping("/users/{userId}/orders/{orderId}")
    public OrderDTO findByOrderId(HttpServletRequest httpServletRequest, @PathVariable long userId, @PathVariable long orderId) {
        orderAccessValidator.isUserValid(userId, httpServletRequest);
        OrderDTO result = orderService.findById(userId, orderId);
        orderDTOHateoas.build(userId, result);
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
    public OrderDTO addOrder(HttpServletRequest httpServletRequest, @PathVariable long userId, @RequestBody OrderDTO orderDTO) {
        orderAccessValidator.isUserValid(userId, httpServletRequest);
        OrderDTO result = orderService.addOrder(userId, orderDTO);
        orderDTOHateoas.build(userId, result);
        return result;
    }
}
