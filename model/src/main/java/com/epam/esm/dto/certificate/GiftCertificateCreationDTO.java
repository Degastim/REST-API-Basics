package com.epam.esm.dto.certificate;

import com.epam.esm.entity.Tag;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Entity of a gift certificate for creation.
 *
 * @author Yauheni Tsitou
 */
public class GiftCertificateCreationDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer duration;
    private List<Tag> tags;

    public GiftCertificateCreationDTO() {
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

    public List<Tag> getTags() {
        return tags;
    }

    public void setTags(List<Tag> tags) {
        this.tags = new ArrayList<>(tags);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GiftCertificateCreationDTO that = (GiftCertificateCreationDTO) o;

        if (tags != null ? !tags.equals(that.tags) : that.tags == null) {
            return false;
        }
        if (name != null ? !name.equals(that.name) : that.name != null) {
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
        int result = duration != null ? duration.hashCode() : 0;
        result = result + 2 * (name != null ? name.hashCode() : 0);
        result = result + 3 * (description != null ? description.hashCode() : 0);
        result = result + 5 * (price != null ? price.hashCode() : 0);
        result = result + 7 * (tags != null ? tags.hashCode() : 0);
        return result;
    }

}
