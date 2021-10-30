package com.epam.esm.hateoas;

import com.epam.esm.controller.OrderController;
import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.PaginationContainer;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/**
 * Converter order according to HATEOAS rules
 *
 * @author Yauheni Tstiov
 */
@Component
public class OrderDTOHateoas {
    /**
     * Converts the orderDTO according to the rules of HATEOAS.
     *
     * @param orderDTO contains an orderDTO for conversion.
     */
    public void build(OrderDTO orderDTO) {
        long orderDTOId = orderDTO.getId();
        Link self = linkTo(methodOn(OrderController.class).findByOrderId(orderDTOId)).withSelfRel();
        orderDTO.add(self);
    }

    /**
     * Converts the order list according to the rules of HATEOAS.
     *
     * @param orderDTOList contains an order for conversion.
     */
    public CollectionModel<OrderDTO> build(List<OrderDTO> orderDTOList, PaginationContainer paginationContainer, long userId) {
        for (OrderDTO orderDTO : orderDTOList) {
            build(orderDTO);
        }
        Link self = linkTo(methodOn(OrderController.class).findAllByUserId(userId, paginationContainer)).withSelfRel();
        Link addOrderLink = linkTo(methodOn(OrderController.class).addOrder(userId, new OrderDTO())).withRel("add_order");
        return CollectionModel.of(orderDTOList, self, addOrderLink);
    }
}