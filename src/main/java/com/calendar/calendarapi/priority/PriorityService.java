package com.calendar.calendarapi.priority;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PriorityService {

    private final PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    public Optional<List<Priority>> getAllPriorities() {
        return Optional.of(priorityRepository.findAll());
    }

    public Optional<Priority> addPriority(Priority priority) {
        return Optional.of(priorityRepository.save(priority));
    }

    public Optional<Priority> updatePriority(Priority priority) {
        return Optional.of(priorityRepository.save(priority));
    }

    public void deletePriority(Priority priority) {
        priorityRepository.delete(priority);
    }

    public void deletePriorityById(long id) {
        priorityRepository.deleteById(id);
    }

    public Optional<Priority> getPriorityById(long id) {
        return priorityRepository.findById(id);
    }

}
