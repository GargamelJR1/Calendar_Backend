package com.calendar.calendarapi.taskGroup;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TaskGroupService {
    private final TaskGroupRepository taskGroupRepository;

    public TaskGroupService(TaskGroupRepository taskGroupRepository) {
        this.taskGroupRepository = taskGroupRepository;
    }

    public Optional<Set<TaskGroup>> getAllTaskGroups() {
        return Optional.of((Set<TaskGroup>) taskGroupRepository.findAll());
    }

    public Optional<TaskGroup> addTaskGroup(TaskGroupDTO taskGroupDTO) {
        TaskGroup taskGroup = new TaskGroup();
        taskGroup.setName(taskGroupDTO.getName());
        taskGroup.setDescription(taskGroupDTO.getDescription());
        taskGroup.setCompleted(taskGroupDTO.isCompleted());
        // Set other properties as needed
        // Save the task group
        return Optional.of(taskGroupRepository.save(taskGroup));
    }

    public Optional<TaskGroup> updateTaskGroup(TaskGroup taskGroup) {
        return Optional.of(taskGroupRepository.save(taskGroup));
    }

    public void deleteTaskGroup(TaskGroup taskGroup) {
        taskGroupRepository.delete(taskGroup);
    }

    public void deleteTaskGroupById(long id) {
        taskGroupRepository.deleteById(id);
    }

    public Optional<TaskGroup> getTaskGroupById(long id) {
        return taskGroupRepository.findById(id);
    }

}
