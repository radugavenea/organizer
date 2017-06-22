package spring.organizer.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Time;

/**
 * Created by radu on 19.06.2017.
 */
@Entity
@Table(name = "timebudget")
public class TimeBudget implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Time totalTime;
    private Time bookedTime;
    private Integer goalId;
    private Double deControl;

    public TimeBudget() {
    }

    public TimeBudget(Integer id, Time totalTime, Time bookedTime, Integer goalId) {
        this.id = id;
        this.totalTime = totalTime;
        this.bookedTime = bookedTime;
        this.goalId = goalId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "total_budget")
    public Time getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Time totalTime) {
        this.totalTime = totalTime;
    }

    @Column(name = "booked_time")
    public Time getBookedTime() {
        return bookedTime;
    }

    public void setBookedTime(Time bookedTime) {
        this.bookedTime = bookedTime;
    }

    @Column(name = "goal_id")
    public Integer getGoalId() {
        return goalId;
    }

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }

}
