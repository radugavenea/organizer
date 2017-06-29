package spring.organizer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.organizer.dto.GoalDTO;
import spring.organizer.entities.Goal;
import spring.organizer.entities.TimeBudget;
import spring.organizer.repositories.GoalRepository;
import spring.organizer.repositories.TimeBudgetRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by radu on 27.06.2017.
 */
@Service
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    @Autowired
    private TimeBudgetRepository timeBudgetRepository;


    public List<Goal> findAllGoalsByUserId(int id){
        List<Goal> goals = goalRepository.findAllByUserId(id);
        return goals.stream()
                .filter(e -> e.getDeleted() == 0)
                .collect(Collectors.toList());
    }

    public Goal findGoalById(int id){
        Goal goal = goalRepository.findById(id);
        return goal.getDeleted() == 0 ? goal : null;
    }


    public Goal insertOrUpdateGoal(Goal goal){
        return goalRepository.save(goal);
    }

    public int deleteGoalById(int id){
        goalRepository.delete(id);
        return id;
        /// should also delete associated events
    }

    public int deleteGoal(Goal goal){
        goal.setDeleted(1);
        goalRepository.save(goal);
        return goal.getId();
    }

    public List<GoalDTO> findAllGoalDTOsById(int id){
        List<Goal> goals = findAllGoalsByUserId(id);
        List<GoalDTO> toReturn = new ArrayList<>();
        TimeBudget timeBudget;
        for(Goal goal : goals){
            timeBudget = timeBudgetRepository.findByGoalId(goal.getId());
            GoalDTO goalDTO = new GoalDTO.Builder()
                    .id(goal.getId())
                    .name(goal.getName())
                    .description(goal.getDescription())
                    .actionPlan(goal.getActionPlan())
                    .progress(goal.getProgress())
                    .example(goal.getExample())
                    .userId(goal.getUserId())
                    .totalBudget(timeBudget.getTotalTime())
                    .availableBudget(timeBudget.getBookedTime())
                    .create();
            toReturn.add(goalDTO);
        }
        return toReturn;
    }

    public void copyGoalProperties(Goal goal, GoalDTO goalDTO) {
        goal.setName(goalDTO.getName());
        goal.setDescription(goalDTO.getDescription());
        goal.setActionPlan(goalDTO.getActionPlan());
        goal.setProgress(goalDTO.getProgress());
        goal.setExample(goalDTO.getExample());
        goal.setUserId(goalDTO.getUserId());
        if(goal.getId() == null){
            goal.setDeleted(0);
        }
    }
}
