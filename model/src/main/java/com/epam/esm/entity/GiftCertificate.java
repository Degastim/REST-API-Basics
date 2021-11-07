package com.epam.esm.entity;

import com.epam.esm.audit.AuditListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

/**
 * Entity of a gift certificate.
 *
 * @author Yauheni Tsitou
 */
@Entity
@Table(name = "gift_certificates")
@EntityListeners(AuditListener.class)
@AttributeOverride(name = "id", column = @Column(name = "gift_certificate_id"))
public class GiftCertificate extends AbstractEntity {
    @Column(name = "gift_certificate_name")
    private String giftCertificateName;
    @Column
    private String description;
    @Column
    private BigDecimal price;
    @Column
    private Integer duration;
    @OneToMany(mappedBy = "giftCertificate", cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH})
    private List<Order> orderList;
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH, CascadeType.DETACH}, fetch = FetchType.EAGER)
    @JoinTable(name = "gift_certificates_tags", joinColumns = @JoinColumn(name = "gift_certificate_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id"))
    private Set<Tag> tags;

    public GiftCertificate() {
    }

    public GiftCertificate(long id) {
        this.id = id;
    }

    public GiftCertificate(long id, String giftCertificateName, String description, BigDecimal price, Integer duration,
                           Set<Tag> tags) {
        this.id = id;
        this.giftCertificateName = giftCertificateName;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.tags = tags;
    }

    public GiftCertificate(String giftCertificateName, String description, BigDecimal price,
                           Integer duration, Set<Tag> tags) {
        this.giftCertificateName = giftCertificateName;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.tags = tags;
    }

    public GiftCertificate(String giftCertificateName, String description, BigDecimal price, Integer duration) {
        this.giftCertificateName = giftCertificateName;
        this.description = description;
        this.price = price;
        this.duration = duration;
    }

    public String getGiftCertificateName() {
        return giftCertificateName;
    }

    public void setGiftCertificateName(String giftCertificateName) {
        this.giftCertificateName = giftCertificateName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        GiftCertificate that = (GiftCertificate) o;
        if (giftCertificateName != null ? !giftCertificateName.equals(that.giftCertificateName) : that.giftCertificateName != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (price != null ? !price.equals(that.price) : that.price != null) {
            return false;
        }
        return duration != null ? duration.equals(that.duration) : that.duration == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = result + 2 * (giftCertificateName != null ? giftCertificateName.hashCode() : 0);
        result = result + 3 * (description != null ? description.hashCode() : 0);
        result = result + 5 * (price != null ? price.hashCode() : 0);
        result = result + 7 * (duration != null ? duration.hashCode() : 0);
        return result;
    }
}
