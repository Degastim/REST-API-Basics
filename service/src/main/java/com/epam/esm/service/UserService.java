package com.epam.esm.service;

import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> finaAll(PaginationContainer paginationContainer);

    UserDTO findById(long id);
}

