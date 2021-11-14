package com.epam.esm.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

/**
 * Entity of a gift certificate for request and response.
 *
 * @author Yauheni Tsitou
 */
public class GiftCertificateDTO extends AbstractDTO<GiftCertificateDTO> {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer duration;
    private LocalDateTime createDate;
    private Set<TagDTO> tags;

    public GiftCertificateDTO() {
    }

    public GiftCertificateDTO(long id, String name, String description, BigDecimal price, Integer duration,
                              LocalDateTime createDate, Set<TagDTO> tags) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.tags = tags;
        this.createDate=createDate;
    }

    public GiftCertificateDTO(String name, String description, BigDecimal price, Integer duration, Set<TagDTO> tags) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.duration = duration;
        this.tags = tags;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<TagDTO> getTags() {
        return tags;
    }

    public void setTags(Set<TagDTO> tags) {
        this.tags = tags;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
            return false;
        }
        GiftCertificateDTO that = (GiftCertificateDTO) o;
        if (tags != null ? !tags.equals(that.tags) : that.tags != null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
            return false;
        }
        if (description != null ? !description.equals(that.description) : that.description != null) {
            return false;
        }
        if (duration != null ? !duration.equals(that.duration) : that.duration != null) {
            return false;
        }
        return price != null ? price.equals(that.price) : that.price == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = result + 2 * (name != null ? name.hashCode() : 0);
        result = result + 3 * (description != null ? description.hashCode() : 0);
        result = result + 5 * (price != null ? price.hashCode() : 0);
        result = result + 7 * (duration != null ? duration.hashCode() : 0);
        result = result + 13 * (tags != null ? tags.hashCode() : 0);
        return result;
    }
}
