package com.epam.esm.validator;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.OrderDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderDTOValidatorTest {
    @Mock
    private GiftCertificateDao giftCertificateDao;

    @Mock
    private UserDao userDao;
    private OrderDTOValidator orderDTOValidator;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderDTOValidator = new OrderDTOValidator(giftCertificateDao, userDao);
    }

    @Test
    void isOrderDTOValid() {
        long giftCertificateId = 3;
        long userId = 3;
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setGiftCertificateId(giftCertificateId);
        Mockito.when(giftCertificateDao.findById(giftCertificateId)).thenReturn(Optional.of(new GiftCertificate()));
        Mockito.when(userDao.findById(userId)).thenReturn(Optional.of(new User()));
        assertDoesNotThrow(() -> orderDTOValidator.isOrderDTOValid(userId, orderDTO));
    }
}