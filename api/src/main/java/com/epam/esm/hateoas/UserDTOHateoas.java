package com.epam.esm.hateoas;

import com.epam.esm.controller.OrderController;
import com.epam.esm.controller.UserController;
import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.UserDTO;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Converter user according to HATEOAS rules
 *
 * @author Yauheni Tstiov
 */
@Component
public class UserDTOHateoas {
    /**
     * Converts the userDTO according to the rules of HATEOAS.
     *
     * @param userDTO contains a user for conversion.
     */
    public void build(UserDTO userDTO) {
        Link self = linkTo(methodOn(UserController.class).findUser(userDTO.getId())).withSelfRel();
        Link ordersListLink = linkTo(methodOn(OrderController.class)
                .findAllByUserId(userDTO.getId(), new PaginationContainer())).withRel("find_user_orders");
        Link addOrderLink = linkTo(methodOn(OrderController.class).addOrder(userDTO.getId(), new OrderDTO()))
                .withRel("add_order");
        userDTO.add(self, ordersListLink, addOrderLink);
        List<OrderDTO> orderDTOList = userDTO.getOrderList();
        if (orderDTOList != null) {
            for (OrderDTO orderDTO : userDTO.getOrderList()) {
                Link orderLink = linkTo(methodOn(OrderController.class).findByOrderId(orderDTO.getId())).withSelfRel();
                orderDTO.add(orderLink);
            }
        }
    }

    /**
     * Converts the user list according to the rules of HATEOAS.
     *
     * @param userDTOList contains a user for conversion.
     */
    public CollectionModel<UserDTO> build(List<UserDTO> userDTOList, PaginationContainer paginationContainer) {
        for (UserDTO userDTO : userDTOList) {
            build(userDTO);
        }
        Link self = linkTo(methodOn(UserController.class).findUserList(paginationContainer)).withSelfRel();
        return CollectionModel.of(userDTOList, self);
    }
}
