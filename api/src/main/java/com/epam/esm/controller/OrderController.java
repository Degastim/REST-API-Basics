package com.epam.esm.controller;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.mapper.OrderDTOMapper;
import com.epam.esm.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;

    }

    @GetMapping("/users/{userId}/orders")
    public List<OrderDTO> findAllByUserId(@PathVariable long userId,@ModelAttribute PaginationContainer paginationContainer) {
        return orderService.findAllByUserId(userId,paginationContainer);
    }

    @GetMapping("/orders/{orderId}")
    public OrderDTO findByOrderId(@PathVariable long orderId) {
        return orderService.findById(orderId);
    }

    @PostMapping("/users/{userId}/orders")
    public OrderDTO addOrder(@PathVariable long userId,@RequestBody OrderDTO orderDTO){
        return orderService.addOrder(userId,orderDTO);
    }
}
