package com.epam.esm.entity.user;

import com.epam.esm.audit.AuditListener;
import com.epam.esm.entity.AbstractEntity;
import com.epam.esm.entity.Order;

import javax.persistence.AttributeOverride;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

/**
 * Entity of a user.
 *
 * @author Yauheni Tsitou
 */
@Entity
@Table(name = "users")
@EntityListeners(AuditListener.class)
@AttributeOverride(name = "id", column = @Column(name = "user_id"))
public class User extends AbstractEntity {
    @Column(name = "user_name")
    private String name;
    @Column
    private String password;
    @Column(name = "is_active")
    private boolean isActive;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orderList;
    @ManyToOne
    @JoinColumn(name = "user_role")
    private UserRole userRole;

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(long id, String name, UserRole userRole, List<Order> orderList) {
        this.id = id;
        this.name = name;
        this.userRole = userRole;
        this.orderList = orderList;
    }

    public User(long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        User that = (User) o;
        return name != null ? name.equals(that.name) : that.name == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result += 2 * (name != null ? name.hashCode() : 0);
        return result;
    }
}
