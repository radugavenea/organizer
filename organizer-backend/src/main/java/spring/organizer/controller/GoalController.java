package spring.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.organizer.dto.GoalDTO;
import spring.organizer.entities.Goal;
import spring.organizer.services.GoalService;

import java.time.Duration;
import java.util.List;

/**
 * Created by radu on 27.06.2017.
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/home")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @RequestMapping(value = "/goals/{id}", method = RequestMethod.GET)
    public List<GoalDTO> getAllGoalsByUserId(@PathVariable("id") int id){
        return goalService.findAllGoalDTOsById(id);
    }
}



