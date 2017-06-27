package spring.organizer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.organizer.entities.TimeBudget;
import spring.organizer.repositories.TimeBudgetRepository;

/**
 * Created by radu on 27.06.2017.
 */
@Service
public class TimeBudgetService {

    @Autowired
    private TimeBudgetRepository timeBudgetRepository;

    public TimeBudget getTimeBudgetByGoalId(int id){
        return timeBudgetRepository.findByGoalId(id);
    }

    public int insertOrUpdateTimeBudget(TimeBudget timeBudget){
        timeBudgetRepository.save(timeBudget);
        return timeBudget.getId();
    }

    public int deleteTimeBudgetById(int id){
        timeBudgetRepository.delete(id);
        return id;
    }
}
