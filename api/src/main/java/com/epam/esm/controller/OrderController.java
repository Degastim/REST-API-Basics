package com.epam.esm.controller;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.hateoas.OrderDTOHateoas;
import com.epam.esm.service.OrderService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Orders controller class.
 *
 * @author Yauheni Tstiov
 */
@RestController
@RequestMapping("/users/{userId}/orders")
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
    @PreAuthorize("hasAuthority('orders:read:all') or (hasAuthority('orders:read') and {@userAccessValidator.isUserValid(#userId,#request)})")
    @GetMapping
    public CollectionModel<OrderDTO> findAllByUserId(HttpServletRequest request, @PathVariable long userId, @ModelAttribute PaginationContainer paginationContainer) {
        List<OrderDTO> orderDTOList = orderService.findAllByUserId(userId, paginationContainer);
        return orderDTOHateoas.build(orderDTOList, paginationContainer, userId);
    }

    /**
     * Find order by ID.
     *
     * @param orderId order id by which we are looking in the database.
     * @return order found in the database by HATEOAS.
     */
    @PreAuthorize("hasAuthority('orders:read:all') or (hasAuthority('orders:read') and {@userAccessValidator.isUserValid(#userId,#request)})")
    @GetMapping("/{orderId}")
    public OrderDTO findByOrderId(HttpServletRequest request, @PathVariable long userId, @PathVariable long orderId) {
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
    @PreAuthorize("hasAuthority('orders:create') and {@userAccessValidator.isUserValid(#userId,#request)}")
    @PostMapping
    public OrderDTO addOrder(HttpServletRequest request, @PathVariable long userId, @RequestBody OrderDTO orderDTO) {
        OrderDTO result = orderService.addOrder(userId, orderDTO);
        orderDTOHateoas.build(userId, result);
        return result;
    }
}
