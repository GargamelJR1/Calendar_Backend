package com.calendar.calendarapi.location;

public class LocationDTO
{
    private double longitude;
    private double latitude;
    private String address;

    public LocationDTO() {
    }

    public LocationDTO(double longitude, double latitude, String address) {
        this.longitude = longitude;
        this.latitude = latitude;
        this.address = address;
    }

    public LocationDTO(Location location) {
        this.longitude = location.getLongitude();
        this.latitude = location.getLatitude();
        this.address = location.getAddress();
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
