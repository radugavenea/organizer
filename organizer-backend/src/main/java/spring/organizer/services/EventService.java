package spring.organizer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.organizer.dto.EventDTO;
import spring.organizer.entities.Event;
import spring.organizer.entities.Goal;
import spring.organizer.repositories.EventRepository;
import spring.organizer.repositories.GoalRepository;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by radu on 28.06.2017.
 */
@Service
public class EventService {

    private DateFormat format2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GoalRepository goalRepository;



    public List<Event> findAll() {
        return eventRepository.findAll();
    }


    public List<EventDTO> findAllByGoalId(int id) {
        List<EventDTO> toReturnList = new ArrayList<>();
        List<Event> events = eventRepository.findAllByGoalId(id).stream()
                .filter(e -> e.getDeleted() == 0)
                .collect(Collectors.toList());
        for (Event event : events) {
            EventDTO eventDTO = copyEventPropertiesToEventDTO(event);
            toReturnList.add(eventDTO);
        }
        return toReturnList;
    }


    public List<EventDTO> findAllByUserId(int id) {
        List<Event> toReturnEvents = new ArrayList<>();
        List<Goal> goals = goalRepository.findAllByUserId(id).stream()
                .filter(e -> e.getDeleted() ==0)
                .collect(Collectors.toList());

        for (Goal goal : goals) {
            List<Event> events = eventRepository.findAllByGoalId(goal.getId()).stream()
                    .filter(e -> e.getDeleted() == 0)
                    .collect(Collectors.toList());
            toReturnEvents.addAll(events);
        }
        return toReturnEvents.stream()
                .map(e -> copyEventPropertiesToEventDTO(e))
                .collect(Collectors.toList());
    }


    public Event insertOrUpdateEvent(Event event) throws ParseException {
        if (event.getId() == null && // check for null so it only execute when insert, and not when update
                timeIntervalNotAvailable(event.getStartDate(), event.getEndDate(), findUserIdForEvent(event))) {
            return null;
        }
        return eventRepository.save(event);
    }



    public Event findById(int id) {
        return eventRepository.findById(id);
    }

    public int deleteEvent(int id) {
        eventRepository.delete(id);
        return id;
    }


    public void deleteAllEventsByGoalId(int id) {
        List<Event> events = eventRepository.findAllByGoalId(id);
        for (Event event : events) {
            event.setDeleted(1);
            eventRepository.save(event);
        }
    }


    public void copyEventProperties(Event event, EventDTO eventDTO) throws ParseException {
        event.setName(eventDTO.getName());
//        event.setStartDate(new Date(format.parse(eventDTO.getStartDate()).getTime()));
//        event.setEndDate(new Date(format.parse(eventDTO.getEndDate()).getTime()));
//        event.setRemainderDate(eventDTO.getRemainderDate().equals("") ? null : new Date(format.parse(eventDTO.getRemainderDate()).getTime()));
        event.setStartDate(LocalDateTime.parse(eventDTO.getStartDate(), format));
        event.setEndDate(LocalDateTime.parse(eventDTO.getEndDate(), format));
        event.setRemainderDate(eventDTO.getRemainderDate().equals("") ? null : LocalDateTime.parse(eventDTO.getRemainderDate(), format));
        event.setNote(eventDTO.getNote());
        event.setGoalId(eventDTO.getGoalId());
        if (event.getId() == null) {
            event.setArchived(0);
            event.setDeleted(0);
        }
    }


    private boolean timeIntervalNotAvailable(LocalDateTime startsAt, LocalDateTime endsAt, int userId) throws ParseException {
        List<EventDTO> events = findAllByUserId(userId);

        int eventsSize = events.size();
        for (int i = 0; i < eventsSize; i++) {

            LocalDateTime eventStartDate = LocalDateTime.parse(events.get(i).getStartDate(), format);
//            LocalDateTime eventEndDate = format2.parse(events.get(i).getEndDate());
            LocalDateTime eventEndDate = LocalDateTime.parse(events.get(i).getEndDate(), format);

            if ((startsAt.compareTo(eventStartDate) > 0 &&
                    startsAt.compareTo(eventEndDate) < 0) ||
                    (endsAt.compareTo(eventStartDate) > 0 &&
                            endsAt.compareTo(eventEndDate) < 0) ||
                    (startsAt.compareTo(eventStartDate) <= 0 &&
                            endsAt.compareTo(eventEndDate) >= 0)) {
                return true;
            }
        }
        return false;
    }

    private int findUserIdForEvent(Event event) {
        return goalRepository.findById(event.getGoalId()).getUserId();
    }


    private EventDTO copyEventPropertiesToEventDTO(Event event) {
        EventDTO eventDTO = new EventDTO.Builder()
                .id(event.getId())
                .name(event.getName())
                .startDate(event.getStartDate())
                .endDate(event.getEndDate())
                .remainderDate(event.getRemainderDate())
                .note(event.getNote())
                .goalId(event.getGoalId())
                .create();
        return eventDTO;
    }

}