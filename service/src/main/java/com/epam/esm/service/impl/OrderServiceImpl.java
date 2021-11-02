package com.epam.esm.service.impl;

import com.epam.esm.Paginator;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.mapper.OrderDTOMapper;
import com.epam.esm.service.OrderService;
import com.epam.esm.validator.OrderDTOValidator;
import com.epam.esm.validator.PaginationContainerValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderDao orderDao;
    private final PaginationContainerValidator paginationContainerValidator;
    private final OrderDTOMapper orderDTOMapper;
    private final Paginator<Order> paginator;
    private final GiftCertificateDao giftCertificateDao;
    private final OrderDTOValidator orderDTOValidator;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, GiftCertificateDao giftCertificateDao, OrderDTOValidator orderDTOValidator, PaginationContainerValidator paginationContainerValidator, OrderDTOMapper orderDTOMapper, Paginator<Order> paginator) {
        this.orderDao = orderDao;
        this.paginationContainerValidator = paginationContainerValidator;
        this.orderDTOMapper = orderDTOMapper;
        this.paginator = paginator;
        this.giftCertificateDao = giftCertificateDao;
        this.orderDTOValidator = orderDTOValidator;
    }

    @Override
    public List<OrderDTO> findAllByUserId(long userId, PaginationContainer paginationContainer) {
        paginationContainerValidator.isPaginationContainerValid(paginationContainer);
        List<Order> orderList = orderDao.findAllByUserId(userId);
        List<Order> paginateList = paginator.paginate(orderList, paginationContainer);
        return paginateList.stream().map(orderDTOMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public OrderDTO findById(long orderId) {
        Optional<Order> optionalOrder = orderDao.findById(orderId);
        if (optionalOrder.isPresent()) {
            return orderDTOMapper.toDTO(optionalOrder.get());
        } else {
            throw new ResourceNotFoundedException("Order with this ID and user ID not found", ExceptionCauseCode.ORDER);
        }
    }

    @Override
    @Transactional
    public OrderDTO addOrder(long userId, OrderDTO orderDTO) {
        orderDTOValidator.isOrderDTOValid(userId, orderDTO);
        Order order = orderDTOMapper.toEntity(orderDTO);
        order.setCreateDate(LocalDateTime.now());
        order.setUser(new User(userId));
        long orderGiftCertificateId = order.getGiftCertificate().getId();
        Optional<GiftCertificate> giftCertificate = giftCertificateDao.findById(orderGiftCertificateId);
        order.setPrice(giftCertificate.get().getPrice());
        orderDao.add(order);
        return orderDTOMapper.toDTO(order);
    }
}
