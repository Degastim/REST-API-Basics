package com.epam.esm.hateoas;

import com.epam.esm.controller.GiftCertificateController;
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
     * @param userId   contains user id.
     * @param orderDTO contains an orderDTO for conversion.
     */
    public void build(long userId, OrderDTO orderDTO) {
        long orderDTOId = orderDTO.getId();
        long giftCertificateId = orderDTO.getGiftCertificateId();
        Link self = linkTo(methodOn(OrderController.class).findByOrderId(null,userId, orderDTOId)).withSelfRel();
        Link giftCertificateLink = linkTo(methodOn(GiftCertificateController.class).findGiftCertificateById(giftCertificateId))
                .withRel("gift_certificate");
        orderDTO.add(self, giftCertificateLink);
    }

    /**
     * Converts the order list according to the rules of HATEOAS.
     *
     * @param orderDTOList contains an order for conversion.
     */
    public CollectionModel<OrderDTO> build(List<OrderDTO> orderDTOList, PaginationContainer paginationContainer, long userId) {
        for (OrderDTO orderDTO : orderDTOList) {
            build(userId, orderDTO);
        }
        Link self = linkTo(methodOn(OrderController.class).findAllByUserId(null,userId, paginationContainer)).withSelfRel();
        Link giftCertificateLink = linkTo(methodOn(GiftCertificateController.class).findGiftCertificates(null, null))
                .withRel("gift_certificate_list");
        return CollectionModel.of(orderDTOList, self, giftCertificateLink);
    }
}