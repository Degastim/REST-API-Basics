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
    @Override
    public Order toEntity(OrderDTO orderDTO) {
        return new Order(orderDTO.getId(), orderDTO.getPrice(), orderDTO.getCreationDate(),
                new GiftCertificate(orderDTO.getGiftCertificateId()));
    }

    @Override
    public OrderDTO toDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO(order.getId(), order.getPrice(), order.getCreateDate());
        GiftCertificate giftCertificate = order.getGiftCertificate();
        if (giftCertificate != null) {
            orderDTO.setGiftCertificateId(giftCertificate.getId());
        } else {
            orderDTO.setGiftCertificateId(0);
        }
        return orderDTO;
    }
}
