package com.epam.esm.validator;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.OrderDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.User;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.InvalidFieldValueException;
import com.epam.esm.exception.ResourceNotFoundedException;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * Validator for order DTO.
 *
 * @author Yauheni Tsitov
 */
@Component
public class OrderDTOValidator {
    private final GiftCertificateDao giftCertificateDao;
    private final UserDao userDao;

    public OrderDTOValidator(GiftCertificateDao giftCertificateDao, UserDao userDao) {
        this.giftCertificateDao = giftCertificateDao;
        this.userDao = userDao;
    }

    /**
     * Is OrderDTO and user id valid .
     *
     * @param orderDTO the object for validation.
     */
    public void isOrderDTOValid(long userId, OrderDTO orderDTO) {
        long giftCertificateId = orderDTO.getGiftCertificateId();
        Optional<GiftCertificate> optionalGiftCertificate = giftCertificateDao.findById(giftCertificateId);
        if (!optionalGiftCertificate.isPresent()) {
            throw new InvalidFieldValueException("No certificate with this ID found=" + giftCertificateId, ExceptionCauseCode.ORDER);
        }
        Optional<User> optionalUser = userDao.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundedException("No certificate with this ID found=" + userId, ExceptionCauseCode.USER);
        }
    }
}
