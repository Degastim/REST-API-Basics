package com.epam.esm.dto;

/**
 * Container for pagination.
 *
 * @author Yauheni Tsitou
 */
public class PaginationContainer {
    private int page;
    private int size;

    public PaginationContainer() {
    }

    public PaginationContainer(int page, int size) {
        this.page = page;
        this.size = size;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PaginationContainer that = (PaginationContainer) o;
        return page == that.page && size == that.size;
    }

    @Override
    public int hashCode() {
        return page + 3 * size;
    }
}
