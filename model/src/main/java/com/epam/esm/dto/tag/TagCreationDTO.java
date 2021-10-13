package com.epam.esm.dto.tag;

/**
 * Entity of a tag for creation.
 *
 * @author Yauheni Tsitou
 */
public class TagCreationDTO {
    private long id;
    private String name;

    public TagCreationDTO() {
    }

    public TagCreationDTO(int id, String name) {
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
        TagCreationDTO tag = (TagCreationDTO) o;
        if (tag.id != id) {
            return false;
        }
        return name != null ? name.equals(tag.name) : tag.name == null;
    }

    @Override
    public int hashCode() {
        return 31 * Long.hashCode(id) + (name != null ? name.hashCode() : 0);
    }
}
