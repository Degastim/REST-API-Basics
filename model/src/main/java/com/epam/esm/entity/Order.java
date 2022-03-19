package com.epam.esm.entity;

import com.epam.esm.audit.AuditListener;
import com.epam.esm.entity.user.User;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity of an order.
 *
 * @author Yauheni Tsitou
 */
@Entity
@Table(name = "orders")
@EntityListeners(AuditListener.class)
@AttributeOverride(name = "id", column = @Column(name = "order_id"))
public class Order extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
    @Column
    private BigDecimal price;
    @ManyToOne
    @JoinColumn(name = "order_gift_certificate_id")
    private GiftCertificate giftCertificate;

    public Order() {
    }

    public Order(long id, BigDecimal price, LocalDateTime createDate, GiftCertificate giftCertificate) {
        this.id = id;
        this.price = price;
        this.createDate = createDate;
        this.giftCertificate = giftCertificate;
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

    public GiftCertificate getGiftCertificate() {
        return giftCertificate;
    }

    public void setGiftCertificate(GiftCertificate giftCertificate) {
        this.giftCertificate = giftCertificate;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
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
        return giftCertificate != null ? giftCertificate.equals(that.giftCertificate) : that.giftCertificate == null;
    }

    @Override
    public int hashCode() {
        int result = Long.hashCode(id);
        result += 2 * (user != null ? user.hashCode() : 0);
        result += 3 * (price != null ? price.hashCode() : 0);
        result += 5 * (giftCertificate != null ? giftCertificate.hashCode() : 0);
        return result;
    }
}
