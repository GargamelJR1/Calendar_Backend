package com.calendar.calendarapi.taskType;

import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

@Service
public class TaskTypeService {

    private final TaskTypeRepository taskTypeRepository;

    public TaskTypeService(TaskTypeRepository taskTypeRepository) {
        this.taskTypeRepository = taskTypeRepository;
    }

    public Optional<Set<TaskType>> getAllTaskTypes() {
        return Optional.of((Set<TaskType>) taskTypeRepository.findAll());
    }

    public Optional<TaskType> addTaskType(TaskTypeDTO taskTypeDTO) {
        TaskType taskType = new TaskType();
        taskType.setName(taskTypeDTO.getName());
        taskType.setDescription(taskTypeDTO.getDescription());
        return Optional.of(taskTypeRepository.save(taskType));
    }

    public Optional<TaskType> updateTaskType(TaskType taskType) {
        return Optional.of(taskTypeRepository.save(taskType));
    }

    public void deleteTaskTypeById(Long id) {
        taskTypeRepository.deleteById(id);
    }

    public Optional<TaskType> getTaskTypeById(Long id) {
        return taskTypeRepository.findById(id);
    }
}
