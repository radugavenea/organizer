package spring.organizer.tests.integration;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.organizer.entities.Event;
import spring.organizer.entities.Goal;
import spring.organizer.entities.TimeBudget;
import spring.organizer.services.EventService;
import spring.organizer.services.GoalService;
import spring.organizer.services.TimeBudgetService;
import spring.organizer.tests.config.TestJPAConfiguration;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertTrue;

/**
 * Created by radu on 30.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestJPAConfiguration.class})
@Transactional
public class GoalIntegrationTest {

    @Autowired
    private GoalService goalService;

    @Autowired
    private TimeBudgetService timeBudgetService;

    @Autowired
    private EventService eventService;

    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");


    @Test
    public void bookedTimeAutoUpdateOnEventInsertTest(){

        Goal goal = new Goal(null, "nume", "descriere", "plan de actiune",
                "progress", "exemplu", 0 ,1);
        Goal goalFromDB = goalService.insertOrUpdateGoal(goal);

        TimeBudget timeBudget = new TimeBudget(null,Duration.ofHours(10),Duration.ofHours(0),
                0,goalFromDB.getId());
        TimeBudget timeBudgetFromDb = timeBudgetService.insertOrUpdateTimeBudget(timeBudget);

        Event event = new Event(null,"nume eveniment",
                        LocalDateTime.parse("2017-07-01 12:00:00",formatter),
                        LocalDateTime.parse("2017-07-01 14:00:00",formatter),
                        LocalDateTime.parse("2017-06-30 12:00:00",formatter),
                        "some note", 0, 0, goalFromDB.getId());

        assertTrue("Booked time before", timeBudgetFromDb.getBookedTime().equals(Duration.ZERO));


        eventService.saveOrUpdateEvent(event);
        timeBudgetService.updateTimeBudgetForNewEvent(event);

//        timeBudgetFromDb = timeBudgetService.findById(timeBudgetFromDb.getId());

        assertTrue("Booked time after", timeBudgetFromDb.getBookedTime().equals(Duration.ofHours(2)));
    }
}
