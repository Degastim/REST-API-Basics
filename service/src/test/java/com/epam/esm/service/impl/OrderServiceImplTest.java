package com.epam.esm.service.impl;

import com.epam.esm.Paginator;
import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.OrderDao;
import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.Order;
import com.epam.esm.exception.ResourceNotFoundedException;
import com.epam.esm.mapper.OrderDTOMapper;
import com.epam.esm.service.OrderService;
import com.epam.esm.validator.OrderDTOValidator;
import com.epam.esm.validator.PaginationContainerValidator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderServiceImplTest {
    @Mock
    private OrderDao orderDao;
    @Mock
    private PaginationContainerValidator paginationContainerValidator;
    @Mock
    private OrderDTOMapper orderDTOMapper;
    @Mock
    private Paginator<Order> paginator;
    @Mock
    private GiftCertificateDao giftCertificateDao;
    @Mock
    private OrderDTOValidator orderDTOValidator;
    private OrderService service;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        service = new OrderServiceImpl(orderDao, giftCertificateDao, orderDTOValidator, paginationContainerValidator,
                orderDTOMapper, paginator);
    }

    @Test
    void findAllByUserId() {
        long id = 3;
        List<Order> list = new ArrayList<>();
        Mockito.when(orderDao.findAllByUserId(id)).thenReturn(list);
        Mockito.when(paginator.paginate(Mockito.any(), Mockito.any())).thenReturn(list);
        List<OrderDTO> actual = service.findAllByUserId(id, new PaginationContainer());
        List<OrderDTO> expected = new ArrayList<>();
        assertEquals(expected, actual);

    }

    @Test
    void findById() {
        long orderId = 3;
        Mockito.when(orderDao.findById(orderId)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundedException.class, () -> service.findById(orderId));
    }

    @Test
    void addOrder() {
        long id = 3;
        OrderDTO orderDTO = new OrderDTO();
        long giftCertificateId = 3;
        orderDTO.setGiftCertificateId(giftCertificateId);
        Order order = new Order();
        GiftCertificate orderGiftCertificate = new GiftCertificate();
        orderGiftCertificate.setId(giftCertificateId);
        order.setGiftCertificate(orderGiftCertificate);
        Mockito.when(orderDTOMapper.toEntity(orderDTO)).thenReturn(order);
        GiftCertificate giftCertificate = new GiftCertificate();
        giftCertificate.setPrice(BigDecimal.TEN);
        Mockito.when(giftCertificateDao.findById(giftCertificateId)).thenReturn(Optional.of(giftCertificate));
        OrderDTO actual = service.addOrder(id, orderDTO);
        Mockito.when(orderDTOMapper.toDTO(order)).thenReturn(orderDTO);
        OrderDTO expected = null;
        assertEquals(expected, actual);
    }
}