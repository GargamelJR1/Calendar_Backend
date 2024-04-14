package com.calendar.calendarapi.event;

import java.util.Date;

public class EventDTO
{
    private String name;

    private String description;

    private Date startDate;

    private Date endDate;

    private long locationId;

    private boolean isPublic;

    public EventDTO() {
    }

    public EventDTO(String name, String description, Date startDate, Date endDate, byte[] image, long locationId, boolean isPublic) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;

        this.locationId = locationId;
        this.isPublic = isPublic;
    }

    public EventDTO(Event event) {
        this.name = event.getName();
        this.description = event.getDescription();
        this.startDate = event.getStartDate();
        this.endDate = event.getEndDate();
        this.locationId = event.getLocation().getId();
        this.isPublic = event.isPublic();
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}