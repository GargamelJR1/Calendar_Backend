package com.calendar.calendarapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner
{

//    private final TaskService taskService;
//    private final EventService eventService;
//
//    public DataLoader(TaskService taskService, EventService eventService) {
//        this.taskService = taskService;
//        this.eventService = eventService;
//    }

    @Override
    public void run(String... args) throws Exception {
        // it was for testing purposes
//        // Add sample tasks
//        Task task1 = new Task();
//        task1.setName("Sample Task 1");
//        task1.setDescription("This is a sample task");
//        task1.setCreatedAt(LocalDate.now().atStartOfDay());
//        task1.setDeadline(LocalDate.now().plusDays(5).atStartOfDay());
//        taskService.addTask(task1);
//
//        Task task2 = new Task();
//        task2.setName("Sample Task 2");
//        task2.setDescription("This is another sample task");
//        task2.setCreatedAt(LocalDate.now().atStartOfDay());
//        task2.setDeadline(LocalDate.now().plusDays(10).atStartOfDay());
//        taskService.addTask(task2);
//
//        // Add sample events
//        Event event1 = new Event();
//        event1.setName("Sample Event 1");
//        event1.setDescription("This is a sample event");
//        event1.setStartDate(LocalDate.now().plusDays(3).atStartOfDay());
//        event1.setEndDate(LocalDate.now().plusDays(4).atStartOfDay());
//        eventService.addEvent(event1);
//
//        Event event2 = new Event();
//        event2.setName("Sample Event 2");
//        event2.setDescription("This is another sample event");
//        event2.setStartDate(LocalDate.now().plusDays(7).atStartOfDay());
//        event2.setEndDate(LocalDate.now().plusDays(8).atStartOfDay());
//        eventService.addEvent(event2);
    }
}