package com.calendar.calendarapi.task;

import com.calendar.calendarapi.tag.Tag;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService
{
    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByDates(LocalDate startDate, LocalDate endDate) {
        return taskRepository.getTasksByDeadlineBetween(startDate.atStartOfDay(), endDate.atStartOfDay().plusDays(1).minusSeconds(1));
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

    public List<Task> getTasksByTag(Tag tag) {
        return getTasksByTagId(tag.getId());
    }

    public List<Task> getTasksByTagId(long tagId) {
        return taskRepository.getTasksByTagId(tagId);
    }

    public List<Task> getCompletedTasks() {
        return taskRepository.getTasksByCompleted(true);
    }

    public List<Task> getUncompletedTasks() {
        return taskRepository.getTasksByCompleted(false);
    }

    public List<Task> getTasksByPriority(String priority) {
        return taskRepository.getTasksByPriority(priority);
    }

    public List<Task> getTasksByMasterTask(Task masterTask) {
        return getTasksByMasterTaskId(masterTask.getId());
    }

    public List<Task> getTasksByMasterTaskId(long masterTaskId) {
        Task masterTask = taskRepository.findById(masterTaskId).orElseThrow(() -> new IllegalArgumentException("Master task not found"));
        return taskRepository.getTasksByMasterTask(masterTask);
    }

    public Optional<Task> addSubTask(Task task) {
        Task masterTask = taskRepository.findById(task.getMasterTask().getId()).orElseThrow(() -> new IllegalArgumentException("Master task not found"));
        task.setMasterTask(masterTask);
        return Optional.of(taskRepository.save(task));
    }
}
