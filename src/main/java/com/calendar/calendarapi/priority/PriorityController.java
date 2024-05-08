package com.calendar.calendarapi.priority;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    private final PriorityService priorityService;

    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping("")
    public ResponseEntity<List<Priority>> getAllPriorities() {
        return priorityService.getAllPriorities()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<Priority> addPriority(@RequestBody Priority priority) {
        return priorityService.addPriority(priority)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("")
    public ResponseEntity<Priority> updatePriority(@RequestBody Priority priority) {
        return priorityService.updatePriority(priority)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePriority(@PathVariable long id) {
        priorityService.deletePriorityById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Priority> getPriorityById(@PathVariable long id) {
        return priorityService.getPriorityById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}

