package spring.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.organizer.dto.EventDTO;
import spring.organizer.services.EventService;
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

}
