package com.calendar.calendarapi.event;

import com.calendar.calendarapi.tag.Tag;
import com.calendar.calendarapi.tag.TagService;
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

    public EventController(EventService eventService, TagService tagService, UserService userService) {
        this.eventService = eventService;
        this.tagService = tagService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<Event>> getAllEvents() {
        return eventService.getAllEvents()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
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

        eventToUpdate.setName(event.getName());
        eventToUpdate.setDescription(event.getDescription());
        eventToUpdate.setStartDate(event.getStartDate());
        eventToUpdate.setEndDate(event.getEndDate());
        eventToUpdate.setLocation(event.getLocation());
        eventToUpdate.setTags(event.getTags());
        eventToUpdate.setUsers(event.getUsers());

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
