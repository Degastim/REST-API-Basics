package com.epam.esm.dto;

/**
 * Entity of a tag for creation.
 *
 * @author Yauheni Tsitou
 */
public class TagDTO extends AbstractCustomDTO<TagDTO> {
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

    @Override
    public boolean equals(Object o) {
        if (!super.equals(o)) {
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
        return super.hashCode() + 31 * (name != null ? name.hashCode() : 0);
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
