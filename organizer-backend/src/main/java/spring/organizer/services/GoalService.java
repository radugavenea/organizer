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
        return goalRepository.findAllByUserId(id);
    }

    public Goal findGoalById(int id){
        return goalRepository.findById(id);
    }

    public Goal insertOrUpdateGoal(Goal goal){
        return goalRepository.save(goal);
//        return goal.getId();
    }

    public void deleteGoalById(int id){
        goalRepository.delete(id);
        /// should also delete associated timeBudget

    }

    public List<GoalDTO> findAllGoalDTOsById(int id){
        List<Goal> goals = findAllGoalsByUserId(id);
        List<GoalDTO> toReturn = new ArrayList<>();
        TimeBudget timeBudget = null;
        for(Goal goal : goals){
            timeBudget = timeBudgetRepository.findByGoalId(goal.getId());
            GoalDTO goalDTO = new GoalDTO.Builder()
                    .id(goal.getId())
                    .name(goal.getName())
                    .description(goal.getDescription())
                    .actionPlan(goal.getActionPlan())
                    .progress(goal.getProgress())
                    .example(goal.getExample())
                    .deleted(goal.getDeleted())
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
        goal.setDeleted(goalDTO.getDeleted());
        goal.setUserId(goalDTO.getUserId());
    }
}
