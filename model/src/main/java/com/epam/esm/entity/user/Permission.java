package com.epam.esm.entity.user;

import com.epam.esm.entity.AbstractEntity;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

/**
 * Permission of user role
 *
 * @author Yauheni Tsitou
 */
@Entity
@Table(name = "permissions")
@AttributeOverride(name = "id", column = @Column(name = "permission_id"))
public class Permission extends AbstractEntity {
    @Column(name = "permission_name")
    private String permissionName;
    @ManyToMany(mappedBy = "permissions", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private Set<UserRole> userRoles;

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public Set<UserRole> getUserRoles() {
        return userRoles;
    }

    public void setUserRoles(Set<UserRole> userRoles) {
        this.userRoles = userRoles;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        Permission that = (Permission) o;
        return permissionName != null ? permissionName.equals(that.permissionName) : that.permissionName == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result += 2 * (permissionName != null ? permissionName.hashCode() : 0);
        return result;
    }
}
