package com.lerson.demomanager.entities;

import java.util.List;

public class DBQuery <T> {

    private List<T> query;

    public DBQuery(List<T> query) {
        this.query = query;
    }

    public List<T> all() {
        return this.query;
    }

    public Boolean exists() {
        if (this.query.size() > 0) {
            return true;
        }

        return false;
    }
}