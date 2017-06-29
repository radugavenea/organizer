package spring.organizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.organizer.entities.Event;

import java.util.List;

/**
 * Created by radu on 28.06.2017.
 */
public interface EventRepository extends JpaRepository<Event, Integer> {

    @Override
    List<Event> findAll();

    List<Event> findAllByGoalId(int id);

    @Override
    Event save(Event s);

    @Override
    Event findOne(Integer integer);

    @Override
    void delete(Integer integer);

    Event findById(int id);
}
