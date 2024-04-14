package com.calendar.calendarapi.location;

import com.calendar.calendarapi.event.Event;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity
public class Location
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private double longitude;

    @NotNull
    private double latitude;

    private String address;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Event> events;

    public Location() {
    }

    public Location(long id, double longitude, double latitude, String address) {
        this.id = id;
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

    public Location(LocationDTO locationDTO) {
        this.longitude = locationDTO.getLongitude();
        this.latitude = locationDTO.getLatitude();
        this.address = locationDTO.getAddress();
    }

    public long getId() {
        return id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
