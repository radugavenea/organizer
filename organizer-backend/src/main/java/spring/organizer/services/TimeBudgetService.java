package spring.organizer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.organizer.dto.GoalDTO;
import spring.organizer.entities.Event;
import spring.organizer.entities.TimeBudget;
import spring.organizer.repositories.TimeBudgetRepository;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

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


    public TimeBudget findById(Integer id) {
        return timeBudgetRepository.findById(id);
    }

    public int deleteTimeBudgetById(int id){
        timeBudgetRepository.delete(id);
        return id;
    }

    public int deleteTimeBudget(TimeBudget timeBudget){
        timeBudget.setDeleted(1);
        timeBudgetRepository.save(timeBudget);
        return timeBudget.getId();
    }

    public void copyTimeBudgetProperties(TimeBudget timeBudget, GoalDTO goalDTO) {
        timeBudget.setTotalTime(Duration.parse("PT" + goalDTO.getTotalBudget() + "H"));
        if(timeBudget.getId() == null) {
            timeBudget.setBookedTime(Duration.parse("PT0H"));
        } else {
            timeBudget.setBookedTime(Duration.parse("PT" + goalDTO.getBookedBudget() + "H"));
        }
        timeBudget.setDeleted(timeBudget.getId() == null ? 0 : timeBudget.getDeleted());
        timeBudget.setGoalId(goalDTO.getId());
    }


    public void updateTimeBudgetForNewEvent(Event event) {
        TimeBudget timeBudget = timeBudgetRepository.findByGoalId(event.getGoalId());

        Duration oldBookedTime = timeBudget.getBookedTime();
        Duration newBookedTime = Duration.between(event.getStartDate(),event.getEndDate());
        if(event.getDeleted() == 0){
            timeBudget.setBookedTime(oldBookedTime.plus(newBookedTime));
        }
        else{
            timeBudget.setBookedTime(oldBookedTime.minus(newBookedTime));
        }
        timeBudgetRepository.save(timeBudget);
    }

    public void updateAllBudgets(List<TimeBudget> timeBudgets){

        for(TimeBudget timeBudget : timeBudgets){
            timeBudgetRepository.save(timeBudget);
        }
    }

}
