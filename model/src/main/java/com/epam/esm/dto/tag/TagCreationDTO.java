package com.epam.esm.dto.tag;

/**
 * Entity of a tag for creation.
 *
 * @author Yauheni Tsitou
 */
public class TagCreationDTO {
    private String name;

    public TagCreationDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TagCreationDTO tag = (TagCreationDTO) o;
        return name != null ? name.equals(tag.name) : tag.name == null;
    }

    @Override
    public int hashCode() {
        return 31 * (name != null ? name.hashCode() : 0);
    }
}
