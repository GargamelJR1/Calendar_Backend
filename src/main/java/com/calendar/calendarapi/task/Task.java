package com.calendar.calendarapi.task;

import com.calendar.calendarapi.priority.Priority;
import com.calendar.calendarapi.taskGroup.TaskGroup;
import com.calendar.calendarapi.taskType.TaskType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private Date deadline;
    @NotNull
    private boolean completed;
    @ManyToOne
    @JoinColumn(name="task_type_id", nullable=false)
    private TaskType type; //priority klucz obcy
    @ManyToOne
    @JoinColumn(name="priority_id", nullable=false)
    private Priority priority; //priority klucz obcy
    @ManyToOne
    @JoinColumn(name="task_group_id",nullable=false)
    private TaskGroup group;
    private int tags; // tag klucz obcy


    public Task() {
    }

    public Task(long id, Date deadline, boolean completed, TaskType type, Priority priority, int tags) {
        this.id = id;
        this.deadline = deadline;
        this.completed = completed;
        this.type = type;
        this.priority = priority;
        this.tags = tags;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
        this.type = type;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public int getTags() {
        return tags;
    }

    public void setTags(int tags) {
        this.tags = tags;
    }
}
