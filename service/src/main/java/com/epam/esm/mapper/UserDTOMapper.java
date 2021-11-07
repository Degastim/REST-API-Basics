package com.epam.esm.mapper;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * User and UserDTO mapper class.
 *
 * @author Yauheni Tstiov
 */
@Component
public class UserDTOMapper implements DTOMapper<User, UserDTO> {
    private final DTOMapper<Order, OrderDTO> orderDTOMapper;

    @Autowired
    public UserDTOMapper(DTOMapper<Order, OrderDTO> orderDTOMapper) {
        this.orderDTOMapper = orderDTOMapper;
    }

    @Override
    public UserDTO toDTO(User user) {
        List<OrderDTO> orderDTOList = user.getOrderList().stream().map(orderDTOMapper::toDTO).collect(Collectors.toList());
        return new UserDTO(user.getId(), user.getName(), orderDTOList);
    }

    @Override
    public User toEntity(UserDTO userDTO) {
        List<Order> orderList = userDTO.getOrderList().stream().map(orderDTOMapper::toEntity).collect(Collectors.toList());
        return new User(userDTO.getId(), userDTO.getName(), orderList);
    }
}
