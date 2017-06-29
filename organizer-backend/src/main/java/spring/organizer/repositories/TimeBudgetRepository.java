package spring.organizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.organizer.entities.TimeBudget;

import java.util.List;

/**
 * Created by radu on 27.06.2017.
 */
public interface TimeBudgetRepository extends JpaRepository<TimeBudget,Integer>{

    @Override
    List<TimeBudget> findAll();

    @Override
    TimeBudget save(TimeBudget timeBudget);

    @Override
    boolean exists(Integer integer);

    @Override
    void delete(Integer integer);

    TimeBudget findByGoalId(int id);

    TimeBudget findById(Integer id);
}
