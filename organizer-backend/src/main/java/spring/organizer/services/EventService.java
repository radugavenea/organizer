package spring.organizer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.organizer.dto.EventDTO;
import spring.organizer.entities.Event;
import spring.organizer.entities.Goal;
import spring.organizer.repositories.EventRepository;
import spring.organizer.repositories.GoalRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by radu on 28.06.2017.
 */
@Service
public class EventService {

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
}
