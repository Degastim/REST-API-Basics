package com.epam.esm.entity;

import com.epam.esm.audit.AuditListener;

import javax.persistence.*;
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
public class Tag extends AbstractEntity {
    @Column(name = "tag_name")
    private String name;
    @ManyToMany(mappedBy = "tags", cascade = CascadeType.ALL)
    private Set<GiftCertificate> giftCertificateSet;

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

    public void addGiftCertificate(GiftCertificate giftCertificate) {
        giftCertificateSet.add(giftCertificate);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        Tag tag = (Tag) o;
        return name != null ? name.equals(tag.name) : tag.name == null;
    }

    @Override
    public int hashCode() {
        return 3 * (name != null ? name.hashCode() : 0) + super.hashCode();
    }
}
