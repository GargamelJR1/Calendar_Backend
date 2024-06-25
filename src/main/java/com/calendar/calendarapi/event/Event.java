package com.calendar.calendarapi.event;

import com.calendar.calendarapi.location.Location;
import com.calendar.calendarapi.tag.Tag;
import com.calendar.calendarapi.user.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Event
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String name;

    private String description;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Lob
    private byte[] image;

    @ManyToOne
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Location location;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "event_tags",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id")}
    )
    private Set<Tag> tags = new HashSet<>();

    @JsonIgnore
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "event_users",
            joinColumns = {@JoinColumn(name = "event_id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id")}
    )
    private Set<User> users = new HashSet<>();

    private String color;

    public Event() {
    }

    public Event(long id, String name, String description, LocalDateTime startDate, LocalDateTime endDate, byte[] image, Location location, Set<Tag> tags, String color) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.image = image;
        this.location = location;
        this.tags = tags;
        this.color = color;
    }

    public Set<Tag> getTags() {
        return tags;
    }

    public void setTags(Set<Tag> tags) {
        this.tags = tags;
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

    public void addTag(Tag tag) {
        if (tags == null)
            tags = new HashSet<>();
        tags.add(tag);
    }

    public void addUser(User user) {
        if (users == null)
            users = new HashSet<>();
        users.add(user);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public void removeTag(Tag tag) {
        tags.remove(tag);
    }

    public void removeUser(User user) {
        users.remove(user);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
