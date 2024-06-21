package com.calendar.calendarapi.task;

import com.calendar.calendarapi.tag.Tag;
import com.calendar.calendarapi.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Task
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String description;

    @NotNull
    private LocalDateTime createdAt;

    private LocalDateTime deadline;

    private LocalDateTime completedAt;

    private boolean completed;

    private String priority;

    @ManyToMany
    @JoinTable(
            name = "task_tags",
            joinColumns = {@JoinColumn(name = "task_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags = new HashSet<>();

    @OneToMany(mappedBy = "masterTask", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Task> subTasks = new HashSet<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "master_task_id")
    private Task masterTask;

    @JsonIgnore
    @ManyToOne
    private User user;

    public Task() {
    }

    public Task(long id, String name, String description, LocalDateTime createdAt, LocalDateTime deadline, LocalDateTime completedAt, boolean completed, String priority, Set<Tag> tags, Task masterTask) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.deadline = deadline;
        this.completedAt = completedAt;
        this.completed = completed;
        this.priority = priority;
        this.tags = tags;
        this.masterTask = masterTask;
    }

    public long getId() {
        return id;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDateTime deadline) {
        this.deadline = deadline;
    }

    public LocalDateTime getCompletedAt() {
        return completedAt;
    }

    public void setCompletedAt(LocalDateTime completedAt) {
        this.completedAt = completedAt;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }

    public Task getMasterTask() {
        return masterTask;
    }

    public void setMasterTask(Task masterTask) {
        this.masterTask = masterTask;
    }

    public void addTag(Tag tag) {
        if (tags == null)
            tags = new HashSet<>();
        this.tags.add(tag);
    }

    public void removeTag(Tag tag) {
        this.tags.remove(tag);
    }

    public void setMasterTaskNull() {
        this.masterTask = null;
    }

    public Set<Task> getSubTasks() {
        return subTasks;
    }

    public void setSubTasks(Set<Task> subTasks) {
        this.subTasks = subTasks;
    }

    public void addSubTask(Task task) {
        this.subTasks.add(task);
    }

    public void removeSubTask(Task task) {
        this.subTasks.remove(task);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
