package com.calendar.calendarapi.task;

import com.calendar.calendarapi.priority.Priority;
import com.calendar.calendarapi.tag.Tag;
import com.calendar.calendarapi.taskGroup.TaskGroup;
import com.calendar.calendarapi.taskType.TaskType;
import com.calendar.calendarapi.user.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Set;

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
    @ManyToOne
    @JoinColumn(name="user_id", nullable=false)
    private User user;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "task_tags",
            joinColumns = { @JoinColumn(name = "task_id") },
            inverseJoinColumns = { @JoinColumn(name = "tag_id") }
    )
    private Set<Tag> tags; // tag klucz obcy




    public Task() {
    }

    public Task(long id, Date deadline, boolean completed, TaskType type, Priority priority, TaskGroup group, User user, Set<Tag> tags) {
        this.id = id;
        this.deadline = deadline;
        this.completed = completed;
        this.type = type;
        this.priority = priority;
        this.group = group;
        this.user = user;
        this.tags = tags;
    }

    public TaskGroup getGroup() {
        return group;
    }

    public void setGroup(TaskGroup group) {
        this.group = group;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
    }
}
