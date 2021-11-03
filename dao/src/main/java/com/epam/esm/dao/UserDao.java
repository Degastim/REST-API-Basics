package com.epam.esm.dao;

import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    /**
     * Finds all the users.
     *
     * @param paginationContainer contaion pagination information.
     * @return list with found users.
     */
    List<User> findAll(PaginationContainer paginationContainer);

    /**
     * Finds user by id.
     *
     * @return optional which contains the found user.
     */
    Optional<User> findById(long id);
}
