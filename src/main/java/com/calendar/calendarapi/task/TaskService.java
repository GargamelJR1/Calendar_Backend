package com.calendar.calendarapi.task;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Optional<Set<Task>> getAllTasks() {
        return Optional.of((Set<Task>) taskRepository.findAll());
    }

    public Optional<Task> addTask(Task task) {
        return Optional.of(taskRepository.save(task));
    }

    public Optional<Task> updateTask(Task task) {
        return Optional.of(taskRepository.save(task));
    }

    public void deleteTask(Task task) {
        taskRepository.delete(task);
    }

    public void deleteTaskById(long id) {
        taskRepository.deleteById(id);
    }

    public Optional<Task> getTaskById(long id) {
        return taskRepository.findById(id);
    }

}
