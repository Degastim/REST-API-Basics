package com.epam.esm.controller;

import com.epam.esm.dto.PaginationContainer;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.hateoas.UserDTOHateoas;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Users controller class.
 *
 * @author Yauheni Tstiov
 */
@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserDTOHateoas userDTOHateoas;

    @Autowired
    public UserController(UserService userService, UserDTOHateoas userDTOHateoas) {
        this.userService = userService;
        this.userDTOHateoas = userDTOHateoas;
    }

    /**
     * Searches for users with pagination.
     *
     * @param paginationContainer contains the desired page and the number of elements per page.
     * @return a list of users by HATEOAS.
     */
    @GetMapping
    public CollectionModel<UserDTO> findUserList(PaginationContainer paginationContainer) {
        List<UserDTO> userList = userService.finaAll(paginationContainer);
        return userDTOHateoas.build(userList, paginationContainer);
    }

    /**
     * Searches for a user by his id.
     *
     * @param id contains user id for search
     * @return found user by HATEOAS.
     */
    @GetMapping("/{id}")
    public UserDTO findUser(@PathVariable long id) {
        UserDTO result = userService.findById(id);
        userDTOHateoas.build(result);
        return result;
    }
}