package com.calendar.calendarapi.task;

import com.calendar.calendarapi.globalModels.Dates;
import com.calendar.calendarapi.tag.Tag;
import com.calendar.calendarapi.tag.TagService;
import com.calendar.calendarapi.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/task")
public class TaskController
{
    private final TagService tagService;
    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TagService tagService, TaskService taskService, UserService userService) {
        this.tagService = tagService;
        this.taskService = taskService;
        this.userService = userService;
    }

    @GetMapping("")
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }

    @PostMapping("")
    public ResponseEntity<List<Task>> getTasksByDates(@RequestBody Dates dates) {
        return ResponseEntity.ok(taskService.getTasksByDates(dates.startDate(), dates.endDate()));
    }

    @GetMapping("/masters")
    public ResponseEntity<List<Task>> getMasterTasks() {
        List<Task> masterTasks = taskService.getAllTasks();
        masterTasks.removeIf(task -> task.getMasterTask() != null);
        return ResponseEntity.ok(masterTasks);
    }

    @PostMapping("/add")
    public ResponseEntity<Task> addTask(@RequestBody TaskDTO taskDTO) {
        Task task = taskDTO.toTask();

        Set<String> tagNames = taskDTO.tags();
        if (tagNames != null) {
            for (String tagName : tagNames) {
                tagService.getTagByName(tagName).ifPresentOrElse(
                        task::addTag,
                        () -> tagService.addTag(new Tag(0, tagName)).ifPresent(task::addTag));
            }
        }

        userService.getUserByEmail(taskDTO.userEmail())
                .ifPresent(task::setUser);

        taskService.getTaskById(taskDTO.masterTask())
                .ifPresent(task::setMasterTask);

        return taskService.addTask(task)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@RequestBody TaskDTO taskDTO, @PathVariable long id) {
        Task taskToUpdate = taskService.getTaskById(id)
                .orElseThrow(() -> new IllegalArgumentException("Task not found"));

        Task task = taskDTO.toTask();

        userService.getUserByEmail(taskDTO.userEmail())
                .ifPresent(task::setUser);

        taskService.getTaskById(taskDTO.masterTask())
                .ifPresent(task::setMasterTask);

        Set<String> tagNames = taskDTO.tags();
        if (tagNames != null) {
            for (String tagName : tagNames) {
                tagService.getTagByName(tagName).ifPresentOrElse(
                        task::addTag,
                        () -> tagService.addTag(new Tag(0, tagName)).ifPresent(task::addTag));
            }
        }

        taskToUpdate.setName(task.getName());
        taskToUpdate.setDescription(task.getDescription());
        taskToUpdate.setDeadline(task.getDeadline());
        taskToUpdate.setCompleted(task.isCompleted());
        taskToUpdate.setPriority(task.getPriority());
        taskToUpdate.setTags(task.getTags());
        taskToUpdate.setMasterTask(task.getMasterTask());
        taskToUpdate.setUser(task.getUser());

        return taskService.updateTask(taskToUpdate)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable long id) {
        taskService.deleteTaskById(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable long id) {
        return taskService.getTaskById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/tag/{tagId}")
    public ResponseEntity<List<Task>> getTasksByTagId(@PathVariable long tagId) {
        return ResponseEntity.ok(taskService.getTasksByTagId(tagId));
    }

    @GetMapping("/completed")
    public ResponseEntity<List<Task>> getCompletedTasks() {
        return ResponseEntity.ok(taskService.getCompletedTasks());
    }

    @GetMapping("/uncompleted")
    public ResponseEntity<List<Task>> getUncompletedTasks() {
        return ResponseEntity.ok(taskService.getUncompletedTasks());
    }

    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getTasksByPriority(@PathVariable String priority) {
        return ResponseEntity.ok(taskService.getTasksByPriority(priority));
    }

    @GetMapping("/master/{masterTaskId}")
    public ResponseEntity<List<Task>> getTasksByMasterTaskId(@PathVariable long masterTaskId) {
        return ResponseEntity.ok(taskService.getTasksByMasterTaskId(masterTaskId));
    }

    @PostMapping("/subtask/add")
    public ResponseEntity<Task> addSubTask(@RequestBody Task task) {
        return taskService.addSubTask(task)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }
}
