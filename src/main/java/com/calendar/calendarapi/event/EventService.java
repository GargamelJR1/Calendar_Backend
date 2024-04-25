package com.calendar.calendarapi.event;

import com.calendar.calendarapi.location.Location;
import com.calendar.calendarapi.location.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService
{
    private final EventRepository eventRepository;
    private final LocationService locationService;

    public EventService(EventRepository eventRepository, LocationService locationService) {
        this.eventRepository = eventRepository;
        this.locationService = locationService;
    }

    public Optional<List<Event>> getAllEvents() {
        return Optional.of(eventRepository.findAll().stream().toList());
    }

    public Optional<Event> addEvent(Event event) {
        return Optional.of(eventRepository.save(event));
    }

    public Optional<Event> addEvent(EventDTO event) {
        Location location = locationService.getLocationById(event.getLocationId())
                .orElseThrow(() -> new IllegalArgumentException("Location not found"));
        Event newEvent = new Event();
        newEvent.setName(event.getName());
        newEvent.setDescription(event.getDescription());
        newEvent.setStartDate(event.getStartDate());
        newEvent.setEndDate(event.getEndDate());
        newEvent.setLocation(location);
        return Optional.of(eventRepository.save(newEvent));
    }

    public Optional<Event> updateEvent(Event event) {
        return Optional.of(eventRepository.save(event));
    }

    public void deleteEvent(Event event) {
        eventRepository.delete(event);
    }

    public void deleteEventById(long id) {
        eventRepository.deleteById(id);
    }

    public Optional<Event> getEventById(long id) {
        return eventRepository.findById(id);
    }

    public Optional<Event> addImageToEvent(long id, byte[] image) {
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Event not found"));
        event.setImage(image);
        return Optional.of(eventRepository.save(event));
    }
}
