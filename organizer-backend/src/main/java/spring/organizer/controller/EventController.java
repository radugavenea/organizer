package spring.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.organizer.dto.EventDTO;
import spring.organizer.entities.Event;
import spring.organizer.errorhandler.ResourceNotFoundException;
import spring.organizer.services.EventService;

import java.text.ParseException;
import java.util.List;

/**
 * Created by radu on 28.06.2017.
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/events")
public class EventController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/byUser/{id}", method = RequestMethod.GET)
    public List<EventDTO> getAllEventsByUserId(@PathVariable("id") int id) {
        return eventService.findAllByUserId(id);
    }

    @RequestMapping(value = "/byGoal/{id}", method = RequestMethod.GET)
    public List<EventDTO> getAllEventsByGoalId(@PathVariable("id") int id) {
        return eventService.findAllByGoalId(id);
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<EventDTO> insertEvent(@RequestBody EventDTO eventDTO) throws ParseException {
        Event event = new Event();
        eventService.copyEventProperties(event,eventDTO);
        eventService.insertOrUpdateEvent(event);
        return new ResponseEntity<EventDTO>(HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<EventDTO> updateEvent(@PathVariable("id") int id, @RequestBody EventDTO eventDTO) throws ParseException {
        Event event;
        try{
             event = eventService.findById(id);
        }
        catch (ResourceNotFoundException ex){
            return new ResponseEntity<EventDTO>(HttpStatus.NOT_FOUND);
        }
        eventService.copyEventProperties(event,eventDTO);
        eventService.insertOrUpdateEvent(event);
        return new ResponseEntity<EventDTO>(HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<EventDTO> deleteEvent(@PathVariable("id") int id){
        try{
            eventService.deleteEvent(id);
        }
        catch (ResourceNotFoundException ex){
            return new ResponseEntity<EventDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<EventDTO>(HttpStatus.NO_CONTENT);
    }


}
