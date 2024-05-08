package com.calendar.calendarapi.taskGroup;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/task-group")
public class TaskGroupController {
    private final TaskGroupService taskGroupService;

    public TaskGroupController(TaskGroupService taskGroupService) {
        this.taskGroupService = taskGroupService;
    }

    @GetMapping("")
    public ResponseEntity<Set<TaskGroup>> getAllTaskGroups() {
        return taskGroupService.getAllTaskGroups()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<TaskGroup> addTaskGroup(@RequestBody TaskGroupDTO taskGroupDTO) {
        return taskGroupService.addTaskGroup(taskGroupDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("")
    public ResponseEntity<TaskGroup> updateTaskGroup(@RequestBody TaskGroup taskGroup) {
        return taskGroupService.updateTaskGroup(taskGroup)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskGroup(@PathVariable long id) {
        taskGroupService.deleteTaskGroupById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskGroup> getTaskGroupById(@PathVariable long id) {
        return taskGroupService.getTaskGroupById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
