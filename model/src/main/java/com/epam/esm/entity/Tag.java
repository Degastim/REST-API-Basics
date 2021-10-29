package com.epam.esm.entity;

import com.epam.esm.audit.AuditListener;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Entity of a tag.
 *
 * @author Yauheni Tsitou
 */
@Entity
@Table(name = "tags")
@EntityListeners(AuditListener.class)
@AttributeOverride(name = "id", column = @Column(name = "tag_id"))
public class Tag extends AbstractCustomEntity {
    @Column(name = "tag_name")
    private String name;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "gift_certificates_tags", joinColumns =@JoinColumn(name = "tag_id"), inverseJoinColumns = @JoinColumn(name = "gift_certificate_id") )
    private Set<GiftCertificate> giftCertificateSet = new HashSet<>();

    public Tag() {
    }

    public Tag(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Tag(String name) {
        this.name = name;
    }

    public Set<GiftCertificate> getGiftCertificateSet() {
        return giftCertificateSet;
    }

    public void setGiftCertificateSet(Set<GiftCertificate> giftCertificateSet) {
        this.giftCertificateSet = giftCertificateSet;
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

    public void addGiftCertificate(GiftCertificate giftCertificate) {
        giftCertificateSet.add(giftCertificate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tag tag = (Tag) o;
        if (id != tag.id) {
            return false;
        }
        return name != null ? name.equals(tag.name) : tag.name == null;
    }

    @Override
    public int hashCode() {
        return 31 * (name != null ? name.hashCode() : 0) + Long.hashCode(id);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tag{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", giftCertificateSet=").append(giftCertificateSet);
        sb.append('}');
        return sb.toString();
    }
}
