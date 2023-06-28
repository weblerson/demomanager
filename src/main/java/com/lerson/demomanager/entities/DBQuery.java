package com.lerson.demomanager.entities;

import java.util.List;

public class DBQuery <T> {

    private List<T> query;

    public List<T> all() {
        return this.query;
    }

    public Boolean exists() {
        if (this.query.size() > 0) {
            return true;
        }

        return false;
    }

    public void add(T entity) {
        this.query.add(entity);
    }
}