package com.calendar.calendarapi.tag;

import java.util.Set;

public class TagDTO {
    private String name;
    private Set<Long> taskIds;
    private Set<Long> eventIds;

    public TagDTO() {
    }

    public TagDTO(String name,  Set<Long> taskIds,  Set<Long> eventIds) {
        this.name = name;
        this.taskIds = taskIds;
        this.eventIds = eventIds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public  Set<Long> getTaskIds() {
        return taskIds;
    }

    public void setTaskIds( Set<Long> taskIds) {
        this.taskIds = taskIds;
    }

    public  Set<Long> getEventIds() {
        return eventIds;
    }

    public void setEventIds( Set<Long> eventIds) {
        this.eventIds = eventIds;
    }

}
