package com.calendar.calendarapi.task;

import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.Set;

public class TaskDTO {

    private Date deadline;
    private boolean completed;
    private long typeId;
    private long priorityId;
    private long groupId;
    private long userId;
    private Set<Long> tagIds;

    public TaskDTO() {
    }

    public TaskDTO(Date deadline, boolean completed, long typeId, long priorityId, long groupId, long userId, Set<Long> tagIds) {
        this.deadline = deadline;
        this.completed = completed;
        this.typeId = typeId;
        this.priorityId = priorityId;
        this.groupId = groupId;
        this.userId = userId;
        this.tagIds = tagIds;
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

    public long getTypeId() {
        return typeId;
    }

    public void setTypeId(long typeId) {
        this.typeId = typeId;
    }

    public long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(long priorityId) {
        this.priorityId = priorityId;
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public Set<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(Set<Long> tagIds) {
        this.tagIds = tagIds;
    }

}
