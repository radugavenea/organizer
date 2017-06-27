package spring.organizer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.organizer.dto.GoalDTO;
import spring.organizer.entities.TimeBudget;
import spring.organizer.repositories.TimeBudgetRepository;

import java.time.Duration;

/**
 * Created by radu on 27.06.2017.
 */
@Service
public class TimeBudgetService {

    @Autowired
    private TimeBudgetRepository timeBudgetRepository;

    public TimeBudget findTimeBudgetByGoalId(int id){
        return timeBudgetRepository.findByGoalId(id);
    }

    public TimeBudget insertOrUpdateTimeBudget(TimeBudget timeBudget){
        return timeBudgetRepository.save(timeBudget);
    }

    public int deleteTimeBudgetById(int id){
        timeBudgetRepository.delete(id);
        return id;
    }

    public void copyTimeBudgetProperties(TimeBudget timeBudget, GoalDTO goalDTO) {
        timeBudget.setTotalTime(Duration.parse("PT" + goalDTO.getTotalBudget() + "H"));
        timeBudget.setBookedTime(Duration.parse("PT" + goalDTO.getBookedBudget() + "H"));
        timeBudget.setGoalId(goalDTO.getId());
    }
}
