package com.epam.esm.dto;

import org.springframework.hateoas.RepresentationModel;

/**
 * Super class for dto
 */
public abstract class AbstractDTO<T extends AbstractDTO<? extends T>> extends RepresentationModel<T> {
    protected long id;

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
        AbstractDTO<?> that = (AbstractDTO<?>) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
