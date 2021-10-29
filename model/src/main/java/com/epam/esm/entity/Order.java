package com.epam.esm.entity;

import com.epam.esm.audit.AuditListener;
import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@EntityListeners(AuditListener.class)
@AttributeOverride(name = "id", column = @Column(name = "order_id"))
public class Order extends AbstractCustomEntity {
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;
    @Column
    private BigDecimal price;
    @Column(name = "create_date")
    private LocalDateTime createDate;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "order_gift_certificate_id",nullable = false)
    private GiftCertificate giftCertificate;
    public Order() {
    }

    public Order(long id, BigDecimal price, LocalDateTime createDate,GiftCertificate giftCertificate) {
        this.id = id;
        this.price = price;
        this.createDate = createDate;
        this.giftCertificate=giftCertificate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public GiftCertificate getGiftCertificate() {
        return giftCertificate;
    }

    public void setGiftCertificate(GiftCertificate giftCertificate) {
        this.giftCertificate = giftCertificate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order that = (Order) o;
        if (user != null ? !user.equals(that.user) : that.user != null) {
            return false;
        }
        if (price != null ? !price.equals(that.price) : that.price != null) {
            return false;
        }
        if (createDate != null ? !createDate.equals(that.createDate) : that.createDate != null) {
            return false;
        }

        return id == that.id;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result += 2 * (user != null ? user.hashCode() : 0);
        result += 3 * (price != null ? price.hashCode() : 0);
        result += 5 * (createDate != null ? createDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("id=").append(id);
        sb.append(", price=").append(price);
        sb.append(", createDate=").append(createDate);
        sb.append('}');
        return sb.toString();
    }
}
