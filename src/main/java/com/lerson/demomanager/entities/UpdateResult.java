package com.lerson.demomanager.entities;

public class UpdateResult {

    private Integer rowsAffected;
    private Integer generatedKeys;

    public UpdateResult() {

    }

    public UpdateResult(Integer rowsAffected, Integer generatedKeys) {
        this.rowsAffected = rowsAffected;
        this.generatedKeys = generatedKeys;
    }

    public Integer getRowsAffected() {
        return rowsAffected;
    }

    public void setRowsAffected(Integer rowsAffected) {
        this.rowsAffected = rowsAffected;
    }

    public Integer getGeneratedKeys() {
        return generatedKeys;
    }

    public void setGeneratedKeys(Integer generatedKeys) {
        this.generatedKeys = generatedKeys;
    }

    @Override
    public String toString() {
        return "UpdateResult(" +
                "rowsAffected=" + rowsAffected +
                ", generatedKeys=" + generatedKeys +
                ')';
    }
}
