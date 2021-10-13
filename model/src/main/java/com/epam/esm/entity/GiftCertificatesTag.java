package com.epam.esm.entity;

public class GiftCertificatesTag {
    private long id;
    private long giftCertificateId;
    private long tagId;

    public GiftCertificatesTag(long id, long giftCertificateId, long tagId) {
        this.id = id;
        this.giftCertificateId = giftCertificateId;
        this.tagId = tagId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getGiftCertificateId() {
        return giftCertificateId;
    }

    public void setGiftCertificateId(long giftCertificateId) {
        this.giftCertificateId = giftCertificateId;
    }

    public long getTagId() {
        return tagId;
    }

    public void setTagId(long tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GiftCertificatesTag that = (GiftCertificatesTag) o;
        return id == that.id && giftCertificateId == that.giftCertificateId && tagId == that.tagId;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id + 2 * giftCertificateId + 3 * tagId);
    }
}
