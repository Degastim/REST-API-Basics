package com.epam.esm.mapper;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import org.springframework.stereotype.Component;

/**
 * Order and OrderDTO mapper class.
 *
 * @author Yauheni Tstiov
 */
@Component
public class OrderDTOMapper implements DTOMapper<Order, OrderDTO> {

    public Order toEntity(OrderDTO orderDTO) {
        return new Order(orderDTO.getId(), orderDTO.getPrice(), orderDTO.getCreationDate(), new GiftCertificate(orderDTO.getGiftCertificateId()));
    }

    public OrderDTO toDTO(Order order) {
        return new OrderDTO(order.getId(), order.getPrice(), order.getCreateDate(), order.getGiftCertificate().getId());
    }
}
