package com.ticket.code.tickets.utils.modelsutils;

public class PaginationModel<T>  {

    private long totalItems;
    private int page;
    private int size;
    private T response;

    public PaginationModel() {
    }

    public PaginationModel(
            long totalItems,
            int page,
            int size,
            T response) {

        this.totalItems = totalItems;
        this.page = page;
        this.size = size;
        this.response = response;
    }

    public long getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(long totalItems) {
        this.totalItems = totalItems;
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

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }

}
