package com.lerson.demomanager.entities;

import java.util.List;

public class DBQuery <T> {

    private List<T> query;

    public List<T> all() {
        return this.query;
    }

    public Boolean exists() {
        return this.query.size() > 0;
    }

    public void add(T entity) {
        this.query.add(entity);
    }
}