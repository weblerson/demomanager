package com.lerson.demomanager.entities;

import java.util.List;
import java.util.ArrayList;

public class DBQuery <T> {

    private List<T> query = new ArrayList<>();

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