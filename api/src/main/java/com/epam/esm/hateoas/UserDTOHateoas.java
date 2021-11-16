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
        long userDTOId = userDTO.getId();
        Link self = linkTo(methodOn(UserController.class).findUser(userDTOId)).withSelfRel();
        Link ordersListLink = linkTo(methodOn(OrderController.class)
                .findAllByUserId(null, userDTOId, new PaginationContainer())).withRel("user_orders");
        List<OrderDTO> orderDTOList = userDTO.getOrderList();
        if (orderDTOList.size() != 0) {
            userDTO.add(ordersListLink);
            for (OrderDTO orderDTO : userDTO.getOrderList()) {
                Link orderLink = linkTo(methodOn(OrderController.class).findByOrderId(null, userDTOId, orderDTO.getId())).withSelfRel();
                orderDTO.add(orderLink);
            }
        }
        userDTO.add(self);
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
