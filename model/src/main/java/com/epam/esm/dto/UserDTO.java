package com.epam.esm.dto;

import org.springframework.hateoas.RepresentationModel;

import java.util.List;

public class UserDTO extends RepresentationModel<UserDTO> {
    private long id;
    private String name;
    private List<OrderDTO> orderList;

    public UserDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public UserDTO(long id, String name, List<OrderDTO> orderList) {
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

    public List<OrderDTO> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<OrderDTO> orderList) {
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
        UserDTO that = (UserDTO) o;
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
        final StringBuilder sb = new StringBuilder("UserDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", orderList=").append(orderList);
        sb.append('}');
        return sb.toString();
    }
}
