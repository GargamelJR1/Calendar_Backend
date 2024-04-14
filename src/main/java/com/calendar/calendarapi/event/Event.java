package com.calendar.calendarapi.event;

import com.calendar.calendarapi.location.Location;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Date;

@Entity
public class Event
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    private String description;

    private Date startDate;

    private Date endDate;

    @Lob
    private byte[] image;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Location location;

    private boolean isPublic;

    // TODO: tags
    // TODO: users

    public Event() {
    }

    public Event(String name, String description, Date startDate, Date endDate, byte[] image, Location location, boolean isPublic) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
        this.location = location;
        this.isPublic = isPublic;
    }

    public Event(EventDTO eventDTO) {
        this.name = eventDTO.getName();
        this.description = eventDTO.getDescription();
        this.startDate = eventDTO.getStartDate();
        this.endDate = eventDTO.getEndDate();
        this.image = eventDTO.getImage();
        this.location = eventDTO.getLocation();
        this.isPublic = eventDTO.isPublic();
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

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }
}
