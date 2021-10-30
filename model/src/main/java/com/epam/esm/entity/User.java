package com.epam.esm.entity;

import com.epam.esm.audit.AuditListener;

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
public class User extends AbstractCustomEntity {
    @Column(name = "user_name")
    private String name;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Order> orderList;

    public User() {
    }

    public User(long id) {
        this.id = id;
    }

    public User(long id, String name, List<Order> orderList) {
        this.id = id;
        this.name = name;
        this.orderList = orderList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User that = (User) o;
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (orderList != null ? !orderList.equals(that.orderList) : that.orderList != null) {
            return false;
        }
        return id == that.id;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result += 2 * (name != null ? name.hashCode() : 0);
        result += 3 * (orderList != null ? orderList.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", orderList=").append(orderList);
        sb.append('}');
        return sb.toString();
    }
}
