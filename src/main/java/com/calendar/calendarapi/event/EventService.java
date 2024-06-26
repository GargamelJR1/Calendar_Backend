package com.calendar.calendarapi.event;

import com.calendar.calendarapi.user.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService
{
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    public Optional<List<Event>> getAllEvents() {
        return Optional.of(eventRepository.findAll().stream().toList());
    }

    public Optional<Event> addEvent(Event event) {
        return Optional.of(eventRepository.save(event));
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

    public Optional<List<Event>> getEventsByUser(User user) {
        return Optional.of(eventRepository.getEventsByUsersContains(user));
    }
}
