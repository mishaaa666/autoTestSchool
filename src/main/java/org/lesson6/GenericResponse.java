package org.lesson6;

import java.util.List;

public class GenericResponse<T> {
    private List<GenericItem<T>> data;
    private Pagination pagination;
    public GenericResponse(List<GenericItem<T>> data, Pagination pagination) {
        this.data = data;
        this.pagination = pagination;
    }

    public List<GenericItem<T>> getData() {
        return data;
    }

    public void setData(List<GenericItem<T>> data) {
        this.data = data;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }
}