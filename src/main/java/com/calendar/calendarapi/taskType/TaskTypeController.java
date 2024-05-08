package com.calendar.calendarapi.taskType;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/task-type")
public class TaskTypeController {
    private final TaskTypeService taskTypeService;

    public TaskTypeController(TaskTypeService taskTypeService) {
        this.taskTypeService = taskTypeService;
    }

    @GetMapping("")
    public ResponseEntity<Set<TaskType>> getAllTaskTypes() {
        return taskTypeService.getAllTaskTypes()
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/add")
    public ResponseEntity<TaskType> addTaskType(@RequestBody TaskTypeDTO taskTypeDTO) {
        return taskTypeService.addTaskType(taskTypeDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("")
    public ResponseEntity<TaskType> updateTaskType(@RequestBody TaskType taskType) {
        return taskTypeService.updateTaskType(taskType)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTaskType(@PathVariable Long id) {
        taskTypeService.deleteTaskTypeById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskType> getTaskTypeById(@PathVariable Long id) {
        return taskTypeService.getTaskTypeById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


}
