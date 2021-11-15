package com.epam.esm.service;

import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.UserCredential;
import com.epam.esm.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
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

    /**
     * Save user in database.
     *
     * @param user the user you want to save
     */
    UserDTO save(UserCredential user);

    /**
     * Find user by name and password.
     *
     * @param name for search.
     * @return found user
     */
    UserDTO findByName(String name);
}

