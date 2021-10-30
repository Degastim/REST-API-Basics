package com.epam.esm.dto;

import org.springframework.hateoas.RepresentationModel;

public abstract class AbstractCustomDTO<T extends AbstractCustomDTO<? extends T>> extends RepresentationModel<T> {
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
        AbstractCustomDTO<?> that = (AbstractCustomDTO<?>) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(id);
    }
}
