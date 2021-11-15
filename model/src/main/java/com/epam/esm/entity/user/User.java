package com.epam.esm.entity.user;

import com.epam.esm.audit.AuditListener;
import com.epam.esm.entity.AbstractEntity;
import com.epam.esm.entity.Order;

import javax.persistence.*;
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
    @Enumerated(EnumType.STRING)
    @Column
    private UserRole role;
    @Column(name = "is_active")
    private boolean isActive;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orderList;

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(long id, String name, UserRole role, List<Order> orderList) {
        this.id = id;
        this.name = name;
        this.role = role;
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

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
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
