package spring.organizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import spring.organizer.entities.Goal;

import java.util.List;

/**
 * Created by radu on 27.06.2017.
 */
public interface GoalRepository extends JpaRepository<Goal, Integer> {

    List<Goal> findAll();

    @Override
    Goal save(Goal s);

    Goal findById(int id);

    @Override
    void delete(Integer id);
}
