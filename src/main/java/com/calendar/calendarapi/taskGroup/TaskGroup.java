package com.calendar.calendarapi.taskGroup;

import com.calendar.calendarapi.task.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

@Entity
public class TaskGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;
    @NotNull
    private String description;
    @NotNull
    private boolean completed;

    @OneToMany(mappedBy = "group")
    private Set<Task> subTasks;

    public TaskGroup() {
    }

    public TaskGroup(long id, String name, String description, boolean completed, Set<Task> subTasks) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.subTasks = subTasks;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public Set<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(Set<Task> subTasks) {
        this.subTasks = subTasks;
    }
}
