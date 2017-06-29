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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by radu on 28.06.2017.
 */
@Service
public class EventService {

    private DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private GoalRepository goalRepository;


    public List<EventDTO> findAllByGoalId(int id){
        List<EventDTO> toReturnList = new ArrayList<>();
        List<Event> events = eventRepository.findAllByGoalId(id);
        for(Event event : events){
            EventDTO eventDTO = new EventDTO.Builder()
                    .id(event.getId())
                    .name(event.getName())
                    .startDate(event.getStartDate())
                    .endDate(event.getEndDate())
                    .remainderDate(event.getRemainderDate())
                    .note(event.getNote())
                    .goalId(event.getGoalId())
                    .create();
            toReturnList.add(eventDTO);
        }
        return toReturnList;
    }


    public List<EventDTO> findAllByUserId(int id) {
        List<Event> toReturnEvents = new ArrayList<>();
        List<Goal> goals = goalRepository.findAllByUserId(id);

        for(Goal goal : goals){
            List<Event> events = eventRepository.findAllByGoalId(goal.getId());
            toReturnEvents.addAll(events);
        }
        return toReturnEvents.stream()
                .map(e -> toEventDTO(e))
                .collect(Collectors.toList());
    }


    public Event insertOrUpdateEvent(Event event) throws ParseException {
//        Event event = new Event(null,
//                eventDTO.getName(),
//                new Date(format.parse(eventDTO.getStartDate()).getTime()),
//                new Date(format.parse(eventDTO.getEndDate()).getTime()),
//                new Date(format.parse(eventDTO.getRemainderDate()).getTime()),
//                eventDTO.getNote(),
//                0,0,
//                eventDTO.getGoalId());
        return eventRepository.save(event);
    }



    private EventDTO toEventDTO(Event event) {
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


    public Event findById(int id) {
        return eventRepository.findById(id);
    }

    public int deleteEvent(int id) {
        eventRepository.delete(id);
        return id;
    }

    public void copyEventProperties(Event event, EventDTO eventDTO) throws ParseException {
        event.setName(eventDTO.getName());
        event.setStartDate(new Date(format.parse(eventDTO.getStartDate()).getTime()));
        event.setEndDate(new Date(format.parse(eventDTO.getEndDate()).getTime()));
        event.setRemainderDate(new Date(format.parse(eventDTO.getRemainderDate()).getTime()));
        event.setNote(eventDTO.getNote());
        event.setGoalId(eventDTO.getGoalId());
        if(event.getId() == null){
            event.setArchived(0);
            event.setDeleted(0);
        }
    }
}
