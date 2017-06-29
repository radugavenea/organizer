package spring.organizer.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;

/**
 * Created by radu on 19.06.2017.
 */

@Entity
@Table(name = "timebudget")
public class TimeBudget implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    private Duration totalTime;
    private Duration bookedTime;
    private Integer deleted;
    private Integer goalId;

    public TimeBudget() {
    }

    public TimeBudget(Integer id, Duration totalTime, Duration bookedTime, Integer deleted, Integer goalId) {
        this.id = id;
        this.totalTime = totalTime;
        this.bookedTime = bookedTime;
        this.deleted = deleted;
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

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }

    @Column(name = "total_budget")
    @Convert(converter = DurationToStringConverter.class)
    public Duration getTotalTime(){
        return this.totalTime;
    }
    public void setTotalTime(Duration time){
        this.totalTime = time;
    }

    @Column(name = "booked_time")
    @Convert(converter = DurationToStringConverter.class)
    public Duration getBookedTime(){
        return this.bookedTime;
    }
    public void setBookedTime(Duration time){
        this.bookedTime = time;
    }

    @Column(name = "deleted")
    public Integer getDeleted(){
        return deleted;
    }
    public void setDeleted(Integer deleted){
        this.deleted = deleted;
    }

    @Column(name = "goal_id")
    public Integer getGoalId() {
        return goalId;
    }

}




