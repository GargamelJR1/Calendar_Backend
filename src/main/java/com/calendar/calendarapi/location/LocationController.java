package com.calendar.calendarapi.location;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/location")
public class LocationController
{
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("")
    public ResponseEntity<LocationDTO> getLocationByCoordinates(@RequestBody Location location) {
        return locationService.getLocationByCoordinates(location.getLatitude(), location.getLongitude())
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public ResponseEntity<List<LocationDTO>> getAllLocations() {
        return locationService.getAllLocations()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<LocationDTO> addLocation(@RequestBody Location location) {
        return locationService.addLocation(location)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("")
    public ResponseEntity<LocationDTO> updateAddress(@RequestBody Location location) {
        return locationService.updateAddress(location)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
