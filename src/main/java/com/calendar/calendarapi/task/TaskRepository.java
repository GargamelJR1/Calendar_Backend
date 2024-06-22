package com.calendar.calendarapi.task;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long>
{
    @Query("SELECT t FROM Task t JOIN t.tags tag WHERE tag.id = :tagId")
    List<Task> getTasksByTagId(long tagId);

    List<Task> getTasksByCompleted(boolean completed);

    List<Task> getTasksByPriority(String priority);

    List<Task> getTasksByMasterTask(Task masterTask);

    List<Task> getTasksByDeadlineBetween(LocalDateTime deadline, LocalDateTime deadline2);
}
