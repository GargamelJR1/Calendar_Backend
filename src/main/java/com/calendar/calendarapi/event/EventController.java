package com.calendar.calendarapi.event;

import com.calendar.calendarapi.location.Location;
import com.calendar.calendarapi.location.LocationService;
import com.calendar.calendarapi.tag.Tag;
import com.calendar.calendarapi.tag.TagService;
import com.calendar.calendarapi.user.User;
import com.calendar.calendarapi.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/event")
public class EventController
{
    private final EventService eventService;
    private final TagService tagService;
    private final UserService userService;
    private final LocationService locationService;

    public EventController(EventService eventService, TagService tagService, UserService userService, LocationService locationService) {
        this.eventService = eventService;
        this.tagService = tagService;
        this.userService = userService;
        this.locationService = locationService;
    }

    @GetMapping("")
    public ResponseEntity<List<Event>> getAllEvents() {
        return eventService.getAllEvents()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{email}")
    public ResponseEntity<List<Event>> getEventsByUserEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email).orElseThrow(() -> new IllegalArgumentException("User not found"));

        List<Event> events = eventService.getEventsByUser(user).orElseThrow(() -> new IllegalArgumentException("Events not found"));

        return ResponseEntity.ok(events);
    }

    @PostMapping("/add")
    public ResponseEntity<Event> addEvent(@RequestBody EventDTO eventDTO) {
        Event event = eventDTO.toEvent();

        Set<String> tagNames = eventDTO.tags();
        if (tagNames != null) {
            for (String tagName : tagNames) {
                tagService.getTagByName(tagName).ifPresentOrElse(
                        event::addTag,
                        () -> tagService.addTag(new Tag(0, tagName)).ifPresent(event::addTag));
            }
        }

        Set<String> userEmails = eventDTO.usersEmails();
        if (userEmails != null) {
            for (String userEmail : userEmails) {
                userService.getUserByEmail(userEmail).ifPresent(event::addUser);
            }
        }

        Location location = locationService.getLocationByCoordinates(eventDTO.latitude(), eventDTO.longitude())
                .orElseGet(() -> locationService.addLocation(
                        new Location(0, eventDTO.latitude(), eventDTO.longitude(), eventDTO.address())
                ).orElseThrow());

        event.setLocation(location);

        return eventService.addEvent(event)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Event> updateEvent(@RequestBody EventDTO eventDTO, @PathVariable long id) {
        Event eventToUpdate = eventService.getEventById(id).orElseThrow(() -> new IllegalArgumentException("Task not found"));
        Event event = eventDTO.toEvent();

        Set<String> tagNames = eventDTO.tags();
        if (tagNames != null) {
            for (String tagName : tagNames) {
                tagService.getTagByName(tagName).ifPresentOrElse(
                        event::addTag,
                        () -> tagService.addTag(new Tag(0, tagName)).ifPresent(event::addTag));
            }
        }

        Set<String> userEmails = eventDTO.usersEmails();
        if (userEmails != null) {
            for (String userEmail : userEmails) {
                userService.getUserByEmail(userEmail).ifPresent(event::addUser);
            }
        }

        Location location = locationService.getLocationByCoordinates(eventDTO.latitude(), eventDTO.longitude())
                .orElseGet(() -> locationService.addLocation(
                        new Location(0, eventDTO.latitude(), eventDTO.longitude(), eventDTO.address())
                ).orElseThrow());

        eventToUpdate.setName(event.getName());
        eventToUpdate.setDescription(event.getDescription());
        eventToUpdate.setStartDate(event.getStartDate());
        eventToUpdate.setEndDate(event.getEndDate());
        eventToUpdate.setLocation(event.getLocation());
        eventToUpdate.setTags(event.getTags());
        eventToUpdate.setUsers(event.getUsers());
        eventToUpdate.setLocation(location);

        return eventService.updateEvent(event)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("")
    public ResponseEntity<Event> deleteEvent(@RequestBody Event event) {
        eventService.deleteEvent(event);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable String id) {
        return eventService.getEventById(Long.parseLong(id))
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add{id}/image")
    public ResponseEntity<Event> addImageToEvent(@PathVariable String id, @RequestBody byte[] image) {
        return eventService.addImageToEvent(Long.parseLong(id), image)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
