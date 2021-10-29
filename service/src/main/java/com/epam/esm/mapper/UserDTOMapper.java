package com.epam.esm.mapper;

import com.epam.esm.dto.OrderDTO;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.entity.Order;
import com.epam.esm.entity.User;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDTOMapper {
    private final OrderDTOMapper orderDTOMapper;

    public UserDTOMapper(OrderDTOMapper orderDTOMapper) {
        this.orderDTOMapper = orderDTOMapper;
    }

    public UserDTO toDTO(User user) {
        List<OrderDTO> orderDTOList = user.getOrderList().stream().map(orderDTOMapper::toDTO).collect(Collectors.toList());
        return new UserDTO(user.getId(), user.getName(), orderDTOList);
    }

    public User toEntity(UserDTO userDTO) {
        List<Order> orderList = userDTO.getOrderList().stream().map(orderDTOMapper::toEntity).collect(Collectors.toList());
        return new User(userDTO.getId(), userDTO.getName(),orderList);
    }
}
