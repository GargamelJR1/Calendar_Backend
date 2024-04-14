package com.calendar.calendarapi.event;

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

}
