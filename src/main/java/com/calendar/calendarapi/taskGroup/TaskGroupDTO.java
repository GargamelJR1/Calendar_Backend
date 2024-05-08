package com.calendar.calendarapi.taskGroup;

import com.calendar.calendarapi.task.TaskDTO;

import java.util.Set;

public class TaskGroupDTO {
    private String name;
    private String description;
    private boolean completed;
    private Set<TaskDTO> subTasks;

    public TaskGroupDTO() {
    }

    public TaskGroupDTO(String name, String description, boolean completed, Set<TaskDTO> subTasks) {
        this.name = name;
        this.description = description;
        this.completed = completed;
        this.subTasks = subTasks;
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

    public Set<TaskDTO> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(Set<TaskDTO> subTasks) {
        this.subTasks = subTasks;
    }
}
