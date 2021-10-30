package com.epam.esm.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * Entity of an order for request and response.
 *
 * @author Yauheni Tsitou
 */
public class OrderDTO extends AbstractCustomDTO<OrderDTO> {
    private BigDecimal price;
    private LocalDateTime creationDate;
    private long giftCertificateId;

    public OrderDTO() {
    }

    public OrderDTO(long id, BigDecimal price, LocalDateTime creationDate, long giftCertificateId) {
        this.id = id;
        this.price = price;
        this.creationDate = creationDate;
        this.giftCertificateId = giftCertificateId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public long getGiftCertificateId() {
        return giftCertificateId;
    }

    public void setGiftCertificateId(long giftCertificateId) {
        this.giftCertificateId = giftCertificateId;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        OrderDTO that = (OrderDTO) o;
        if (price != null ? !price.equals(that.price) : that.price != null) {
            return false;
        }
        if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) {
            return false;
        }
        return giftCertificateId == that.giftCertificateId;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result += 3 * (price != null ? price.hashCode() : 0);
        result += 5 * (creationDate != null ? creationDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OrderDTO{");
        sb.append("id=").append(id);
        sb.append(", price=").append(price);
        sb.append(", creationDate=").append(creationDate);
        sb.append('}');
        return sb.toString();
    }
}
