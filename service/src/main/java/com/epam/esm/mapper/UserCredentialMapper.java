package com.epam.esm.mapper;

import com.epam.esm.dto.UserCredential;
import com.epam.esm.entity.user.User;
import org.springframework.stereotype.Component;

/**
 * User and UserCredential mapper class.
 *
 * @author Yauheni Tstiov
 */
@Component
public class UserCredentialMapper implements DTOMapper<User, UserCredential> {

    @Override
    public User toEntity(UserCredential dto) {
        return new User(dto.getId(), dto.getName(), dto.getPassword());
    }

    @Override
    public UserCredential toDTO(User entity) {
        return new UserCredential(entity.getId(), entity.getName(), entity.getPassword());
    }
}
