package com.epam.esm.validator;

import com.epam.esm.dao.GiftCertificateDao;
import com.epam.esm.dao.UserDao;
import com.epam.esm.dto.OrderDTO;
import com.epam.esm.entity.GiftCertificate;
import com.epam.esm.entity.user.User;
import com.epam.esm.error.ExceptionCauseCode;
import com.epam.esm.exception.InvalidFieldValueException;
import com.epam.esm.exception.ResourceNotFoundedException;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
        StringBuilder errorMessage = new StringBuilder();
        long id = orderDTO.getId();
        if (id != 0) {
            errorMessage.append("Can't transfer id order.");
        }
        BigDecimal price = orderDTO.getPrice();
        if (price != null) {
            errorMessage.append("Can't transfer price order.");
        }
        LocalDateTime createDate = orderDTO.getCreationDate();
        if (createDate != null) {
            errorMessage.append("Can't transfer create date order.");
        }
        if (errorMessage.length() != 0) {
            throw new InvalidFieldValueException(errorMessage.toString(), ExceptionCauseCode.ORDER);
        }
        long giftCertificateId = orderDTO.getGiftCertificateId();
        Optional<GiftCertificate> optionalGiftCertificate = giftCertificateDao.findById(giftCertificateId);
        if (!optionalGiftCertificate.isPresent()) {
            throw new ResourceNotFoundedException("No certificate with this ID found=" + userId, ExceptionCauseCode.USER);
        }
        Optional<User> optionalUser = userDao.findById(userId);
        if (!optionalUser.isPresent()) {
            throw new ResourceNotFoundedException("No user with this ID found=" + userId, ExceptionCauseCode.USER);
        }
    }
}
