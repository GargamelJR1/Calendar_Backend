package com.calendar.calendarapi.task;

import java.time.LocalDateTime;
import java.util.Set;

public record TaskDTO
        (
                String name,
                String description,
                LocalDateTime createdAt,
                LocalDateTime deadline,
                LocalDateTime completedAt,
                boolean completed,
                String priority,
                Set<String> tags,
                Integer masterTask,
                String userEmail
        )
{
    Task toTask() {
        return new Task(0, name, description, createdAt, deadline, completedAt, completed, priority, null, null);
    }
}
