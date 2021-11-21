package com.epam.esm.entity.user;

import com.epam.esm.entity.AbstractEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Role of a user.
 *
 * @author Yauheni Tsitou
 */
@Entity
@Table(name = "user_roles")
@AttributeOverride(name = "id", column = @Column(name = "user_role_id"))
public class UserRole extends AbstractEntity {
    @Column(name = "user_role_name")
    private String userRoleName;
    @OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL)
    private Set<User> userSet;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles_permissions", joinColumns = @JoinColumn(name = "user_role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id"))
    private Set<Permission> permissions;

    public UserRole() {
    }

    public UserRole(String userRoleName) {
        this.userRoleName = userRoleName;
    }

    public String getUserRoleName() {
        return userRoleName;
    }

    public void setUserRoleName(String userRole) {
        this.userRoleName = userRole;
    }

    public Set<User> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<User> userSet) {
        this.userSet = userSet;
    }

    public Set<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<Permission> permissions) {
        this.permissions = permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        UserRole userRole = (UserRole) o;
        return userRoleName != null ? userRoleName.equals(userRole.userRoleName) : userRole.userRoleName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        return result + userRoleName.hashCode();
    }
}
