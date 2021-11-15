package com.epam.esm.controller;

import com.epam.esm.dto.UserCredential;
import com.epam.esm.dto.UserDTO;
import com.epam.esm.hateoas.UserDTOHateoas;
import com.epam.esm.security.JwtTokenProvider;
import com.epam.esm.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Authentication controller class.
 *
 * @author Yauheni Tstiov
 */
@RestController
@RequestMapping("/auth")
public class AuthenticationController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final UserDTOHateoas userDTOHateoas;

    @Autowired
    public AuthenticationController(AuthenticationManager authenticationManager, UserService userService,
                                    JwtTokenProvider jwtTokenProvider, UserDTOHateoas userDTOHateoas) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDTOHateoas = userDTOHateoas;
    }

    /**
     * Login in application.
     *
     * @param credential contain param for login.
     * @return list with found items by HATEOAS.
     */
    @PostMapping("/login")
    public String login(@RequestBody UserCredential credential) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(credential.getName(), credential.getPassword()));
        UserDTO userDTO = userService.findByName(credential.getName());
        return jwtTokenProvider.createToken(userDTO.getName(), userDTO.getRole().toString());
    }

    /**
     * Register new user.
     *
     * @param userCredential contain param for create.
     * @return list with found items by HATEOAS.
     */
    @PostMapping("/register")
    public UserDTO register(@RequestBody UserCredential userCredential) {
        UserDTO userDTO = userService.save(userCredential);
        userDTOHateoas.build(userDTO);
        return userDTO;
    }
}
