package com.epam.esm.service;

import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.UserDTO;

import java.util.List;

public interface UserService {
    /**
     * Searches for all users.
     *
     * @param paginationContainer contains pagination options
     * @return list of found users
     */
    List<UserDTO> finaAll(PaginationContainer paginationContainer);

    /**
     * Searches for a user by his id
     *
     * @param id the user you want to find
     * @return found user
     */
    UserDTO findById(long id);
}

