package spring.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.organizer.dto.GoalDTO;
import spring.organizer.entities.Goal;
import spring.organizer.entities.TimeBudget;
import spring.organizer.errorhandler.ResourceNotFoundException;
import spring.organizer.services.GoalService;
import spring.organizer.services.TimeBudgetService;

import java.time.Duration;
import java.util.List;

/**
 * Created by radu on 27.06.2017.
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/goals")
public class GoalController {

    @Autowired
    private GoalService goalService;

    @Autowired
    private TimeBudgetService timeBudgetService;

    @RequestMapping(value = "/all/{id}", method = RequestMethod.GET)
    public List<GoalDTO> getAllGoalsByUserId(@PathVariable("id") int id){
        return goalService.findAllGoalDTOsById(id);
    }


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<GoalDTO> insertGoal(@RequestBody GoalDTO goalDTO){
        Goal goal = new Goal();
//                null,
//                goalDTO.getName(),
//                goalDTO.getDescription(),
//                goalDTO.getActionPlan(),
//                goalDTO.getProgress(),
//                goalDTO.getExample(),
//                0,  // deleted
//                goalDTO.getUserId()
//        );
        goalService.copyGoalProperties(goal,goalDTO);
        int goalId = goalService.insertOrUpdateGoal(goal).getId();

        goalDTO.setId(goalId);
        TimeBudget timeBudget = new TimeBudget();
//                (
//                null,
//                Duration.parse("PT" + goalDTO.getTotalBudget() + "H"),
//                Duration.parse("PT0H"),
//                goalId
//        );
        timeBudgetService.copyTimeBudgetProperties(timeBudget,goalDTO);
        timeBudgetService.insertOrUpdateTimeBudget(timeBudget);
        return new ResponseEntity<>(goalDTO, HttpStatus.CREATED);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<GoalDTO> updateGoal(@PathVariable("id") int id,@RequestBody GoalDTO goalDTO){
        Goal goal;
        TimeBudget timeBudget;
        try{
            goal = goalService.findGoalById(id);
            timeBudget = timeBudgetService.findTimeBudgetByGoalId(id);
        }
        catch (ResourceNotFoundException ex){
            return new ResponseEntity<GoalDTO>(HttpStatus.NOT_FOUND);
        }
        goalService.copyGoalProperties(goal,goalDTO);
        timeBudgetService.copyTimeBudgetProperties(timeBudget,goalDTO);

        goalService.insertOrUpdateGoal(goal);
        timeBudgetService.insertOrUpdateTimeBudget(timeBudget);
        return new ResponseEntity<GoalDTO>(goalDTO,HttpStatus.OK);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<GoalDTO> deleteGoalById(@PathVariable("id") int id){
        TimeBudget timeBudget;
        try{
            timeBudget = timeBudgetService.findTimeBudgetByGoalId(id);
            timeBudgetService.deleteTimeBudgetById(timeBudget.getId());
            goalService.deleteGoalById(id);
        }
        catch (ResourceNotFoundException ex){
            return new ResponseEntity<GoalDTO>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<GoalDTO>(HttpStatus.NO_CONTENT);
    }

}



