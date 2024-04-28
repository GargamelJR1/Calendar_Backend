package com.calendar.calendarapi.task;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class TaskDTO {

    private Date deadline;
    private boolean completed;

    public TaskDTO() {
    }

    public TaskDTO(Date deadline, boolean completed) {
        this.deadline = deadline;
        this.completed = completed;
    }

    public TaskDTO(Task task){
        this.deadline = task.getDeadline();
        this.completed = task.isCompleted();
    }
    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
