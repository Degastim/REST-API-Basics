package com.epam.esm.dao;

import com.epam.esm.entity.user.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRoleDao extends JpaRepository<UserRole, Long> {
    Optional<UserRole> findByUserRoleName(String userRoleName);
}
