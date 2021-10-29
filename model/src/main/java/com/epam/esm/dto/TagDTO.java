package com.epam.esm.dto;

import org.springframework.hateoas.RepresentationModel;

/**
 * Entity of a tag for creation.
 *
 * @author Yauheni Tsitou
 */
public class TagDTO extends RepresentationModel<TagDTO> {
    private long id;
    private String name;

    public TagDTO() {
    }

    public TagDTO(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TagDTO tag = (TagDTO) o;
        if (tag.id != id) {
            return false;
        }
        return name != null ? name.equals(tag.name) : tag.name == null;
    }

    @Override
    public int hashCode() {
        return 31 * Long.hashCode(id) + (name != null ? name.hashCode() : 0);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("TagDTO{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
