package com.calendar.calendarapi.event;

import java.time.LocalDateTime;

public class EventDTO
{
    private String name;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private long locationId;


    public EventDTO() {
    }

    public EventDTO(String name, String description, LocalDateTime startLocalDateTime, LocalDateTime endLocalDateTime, byte[] image, long locationId) {
        this.name = name;
        this.description = description;
        this.startDate = startLocalDateTime;
        this.endDate = endLocalDateTime;

        this.locationId = locationId;
    }

    public EventDTO(Event event) {
        this.name = event.getName();
        this.description = event.getDescription();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.locationId = event.getLocation().getId();
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }
}
