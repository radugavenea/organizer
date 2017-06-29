package spring.organizer.tests.integration;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import spring.organizer.dto.EventDTO;
import spring.organizer.entities.Event;
import spring.organizer.services.EventService;
import spring.organizer.tests.config.TestJPAConfiguration;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.junit.Assert.assertTrue;

/**
 * Created by radu on 29.06.2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestJPAConfiguration.class})
@Transactional
public class EventServiceTest {

    @Autowired
    private EventService eventService;

        private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    private Event event1, event2;


    @Before
    public void init(){

        event1 = new Event(null,"nume eveniment",
                LocalDateTime.parse("2017-07-01 12:00:00",formatter),
                LocalDateTime.parse("2017-07-01 14:00:00",formatter),
                LocalDateTime.parse("2017-06-30 12:00:00",formatter),
                "some note", 0, 0, 1);
        event2 = new Event(null,"eveniment2",
                LocalDateTime.parse("2017-07-01 14:00:00",formatter),
                LocalDateTime.parse("2017-07-01 15:20:00",formatter),
                LocalDateTime.parse("2017-06-30 11:00:00",formatter),
                "da da da", 0, 0, 1);
    }

    @Test
    public void insertEventTest() throws ParseException {

        eventService.saveOrUpdateEvent(event1);
        eventService.saveOrUpdateEvent(event2);

        List<Event> fromDB = eventService.findAll();

        assertTrue("One entity inserted", fromDB.size() == 2);
    }

    @Test
    public void getEventByIdSuccessfulTest() throws ParseException {

        int eventId = eventService.saveOrUpdateEvent(event1);
        Event fromDB = eventService.findById(eventId);

        assertTrue("Name ", event1.getName().equals(fromDB.getName()));
        assertTrue("Email ", event1.getStartDate().equals(fromDB.getStartDate()));
        assertTrue("Role ", event1.getNote().equals(fromDB.getNote()));
    }


    @Test
    public void getEventByGoalIdTest() throws ParseException {

        eventService.saveOrUpdateEvent(event1);
        eventService.saveOrUpdateEvent(event2);
        List<EventDTO> fromDB = eventService.findAllByGoalId(1);

        assertTrue("Name ", event1.getName().equals(fromDB.get(0).getName()));
        assertTrue("Name ", event2.getName().equals(fromDB.get(1).getName()));
    }

    @Test
    public void updateEventTest(){

        int eventId = eventService.saveOrUpdateEvent(event1);
        Event fromDB = eventService.findById(eventId);

        assertTrue("Name ", fromDB.getName().equals("nume eveniment"));
        assertTrue("Name ", event1.getName().equals(fromDB.getName()));

        fromDB.setName("noul nume");
        int eventId2 = eventService.saveOrUpdateEvent(fromDB);
        Event fromDB2 = eventService.findById(eventId2);

        assertTrue("New Name ", fromDB2.getName().equals("noul nume"));
        assertTrue("New Name ", event1.getName().equals(fromDB.getName()));
    }

    @Test
    public void deleteTest(){

        eventService.saveOrUpdateEvent(event1);
        eventService.saveOrUpdateEvent(event2);
        List<Event> fromDB = eventService.findAll();

        eventService.deleteEvent(event1.getId());
        List<Event> fromDB2 = eventService.findAll();

        assertTrue("One entity inserted", fromDB2.size() == fromDB.size() - 1);
    }

}
