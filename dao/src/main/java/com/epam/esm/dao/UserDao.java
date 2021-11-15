package com.epam.esm.dao;

import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.entity.user.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    /**
     * Finds all the users.
     *
     * @param paginationContainer contain pagination information.
     * @return list with found users.
     */
    List<User> findAll(PaginationContainer paginationContainer);

    /**
     * Finds user by id.
     *
     * @return optional which contains the found user.
     */
    Optional<User> findById(long id);

    /**
     * Finds user by name.
     *
     * @return optional which contains the found user.
     */
    Optional<User> findByName(String name);

    /**
     * Save user
     *
     * @param user for save.
     */
    void save(User user);
}
