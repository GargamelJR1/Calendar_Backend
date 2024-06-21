package com.calendar.calendarapi.event;

import java.time.LocalDateTime;
import java.util.Set;

public record EventDTO
        (
                String name,
                String description,
                LocalDateTime startDate,
                LocalDateTime endDate,
                Set<String> tags,
                Set<String> usersEmails,
                double latitude,
                double longitude,
                String address
        )
{
    Event toEvent() {
        return new Event(0, name, description, startDate, endDate, null, null, null);
    }
}
