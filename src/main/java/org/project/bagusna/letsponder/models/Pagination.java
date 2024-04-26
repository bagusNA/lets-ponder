package org.project.bagusna.letsponder.models;

import java.util.ArrayList;

public class Pagination<T> {
    protected Integer page;
    protected Integer perPage;
    protected Integer totalPages;
    protected Integer totalItems;
    protected ArrayList<T> items;

    public Pagination(Integer page, Integer perPage, Integer totalPages, Integer totalItems, ArrayList<T> items) {
        this.page = page;
        this.perPage = perPage;
        this.totalPages = totalPages;
        this.totalItems = totalItems;
        this.items = items;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalItems() {
        return totalItems;
    }

    public void setTotalItems(Integer totalItems) {
        this.totalItems = totalItems;
    }

    public ArrayList<T> getItems() {
        return items;
    }

    public void setItems(ArrayList<T> items) {
        this.items = items;
    }
}
