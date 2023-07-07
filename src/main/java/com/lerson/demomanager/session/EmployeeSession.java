package com.lerson.demomanager.session;

public class EmployeeSession {

    public static EmployeeSession instance = new EmployeeSession();

    private Integer id = null;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void clearSession() {
        this.id = null;
    }
}
