package com.calendar.calendarapi.tag;

import com.calendar.calendarapi.event.Event;
import com.calendar.calendarapi.task.Task;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;
import java.util.Set;

@Entity
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotNull
    private String name;

    @ManyToMany(mappedBy = "tags")
    private Set<Task> tasks;
    @ManyToMany(mappedBy = "tags")
    private Set<Event> events;
    public Tag() {
    }

    public Tag(long id, String name, Set<Task> tasks) {
        this.id = id;
        this.name = name;
        this.tasks = tasks;
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

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }
}
