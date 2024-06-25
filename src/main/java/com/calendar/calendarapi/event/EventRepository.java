package com.calendar.calendarapi.event;

import com.calendar.calendarapi.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long>
{
    List<Event> getEventsByUsersContains(User user);
}
