package com.epam.esm.controller;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public CollectionModel<UserDTO> findUserList(PaginationContainer paginationContainer) {
        List<UserDTO> userList = userService.finaAll(paginationContainer);
        for (UserDTO userDTO : userList) {
            WebMvcLinkBuilder builder = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).findUser(userDTO.getId()));
            Link self = builder.withSelfRel();
            Link ordersListLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).findAllByUserId(userDTO.getId(),new PaginationContainer())).withRel("find_user_orders");
            userDTO.add(self,ordersListLink);
            List<OrderDTO> orderDTOList = userDTO.getOrderList();
            if (orderDTOList != null) {
                for (OrderDTO orderDTO : userDTO.getOrderList()) {
                    Link orderLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(OrderController.class).findByOrderId(orderDTO.getId())).withSelfRel();
                    orderDTO.add(orderLink);
                }
            }
        }
        Link self = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(UserController.class).findUserList(paginationContainer)).withSelfRel();
        return CollectionModel.of(userList, self);
    }

    @GetMapping("/{id}")
    public UserDTO findUser(@PathVariable long id) {
        return userService.findById(id);
    }
}