package com.calendar.calendarapi.location;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService
{
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    private final double MIN_DISTANCE_BETWEEN_LOCATIONS = 0.0001f;

    public Optional<Location> getLocationById(long id) {
        return locationRepository.findById(id);
    }

    public Optional<Location> addLocation(Location location) {
        for (Location existingLocation : locationRepository.findAll()) {
            if (distanceBetweenLocations(location, existingLocation) < MIN_DISTANCE_BETWEEN_LOCATIONS) {
                return Optional.of(existingLocation);
            }
        }
        return Optional.of(locationRepository.save(location));
    }

    public Optional<List<Location>> getAllLocations() {
        return Optional.of(locationRepository.findAll().stream().toList());
    }

    public Optional<Location> getLocationByCoordinates(double latitude, double longitude) {
        for (Location location : locationRepository.findAll()) {
            if (distanceBetweenLocations(location, new Location(0, latitude, longitude, "")) < MIN_DISTANCE_BETWEEN_LOCATIONS) {
                return Optional.of(location);
            }
        }
        return Optional.empty();
    }

    public Optional<Location> updateAddress(Location location) {
        return Optional.of(locationRepository.save(location));
    }

    private double distanceBetweenLocations(Location location1, Location location2) {
        return Math.sqrt(Math.pow(location1.getLatitude() - location2.getLatitude(), 2) + Math.pow(location1.getLongitude() - location2.getLongitude(), 2));
    }
}
