package com.lerson.demomanager.entities;

import java.time.LocalDateTime;

public class Job {

    private final Integer id;
    private final String clientName;
    private final Integer type;
    private Integer status;
    private LocalDateTime deadline;
    private String description;

    public Job(Integer id, String clientName, Integer type,
               Integer status, LocalDateTime deadline, String description) {
        this.id = id;
        this.clientName = clientName;
        this.type = type;
        this.status = status;
        this.deadline = deadline;
        this.description = description;
    }

    public Integer getId() {
        return id;
    }

    public String getClientName() {
        return clientName;
    }

    public Integer getType() {
        return type;
    }

    public Integer getStatus() {
        return status;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Job(" +
                "id=" + id +
                ", clientName='" + clientName + '\'' +
                ", type=" + type +
                ", status=" + status +
                ", deadline=" + deadline +
                ", description='" + description + '\'' +
                ')';
    }
}
