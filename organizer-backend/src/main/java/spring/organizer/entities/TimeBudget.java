package spring.organizer.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Duration;
import java.util.Date;

/**
 * Created by radu on 19.06.2017.
 */
@Entity
@Table(name = "timebudget")
public class TimeBudget implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer id;
    @Transient
    private Duration totalTime;
    @Transient
    private Duration bookedTime;
    private Integer goalId;
    private String totalTimeString;
    private String bookedTimeString;

    public TimeBudget() {
    }

    public TimeBudget(Integer id, Duration totalTime, Duration bookedTime, Integer goalId) {
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


    @Column(name = "goal_id")
    public Integer getGoalId() {
        return goalId;
    }

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }


    @Column(name = "total_budget")
    public String getTotalTimeString(){
        return totalTimeString;
    }
    public void setTotalTimeString(String time){
        this.totalTimeString = time;
    }

    @Column(name = "booked_time")
    public String getBookedTimeString(){
        return bookedTimeString;
    }
    public void setBookedTimeString(String time){
        this.bookedTimeString = time;
    }


    @PostLoad
    public void init(){
        this.totalTime = this.totalTimeString == null ? null : Duration.parse(this.totalTimeString);
        this.bookedTime = this.bookedTimeString == null ? null : Duration.parse(this.bookedTimeString);
    }

    @Transient
    public Duration getTotalTime() {
        return this.totalTime;
    }

    public void setTotalTime(Duration totalTime) {
        this.totalTimeString = totalTime == null ? null : totalTime.toString();
    }

    @Transient
    public Duration getBookedTime() {
        return this.bookedTime;
    }

    public void setBookedTime(Duration bookedTime) {
        this.bookedTimeString = bookedTime == null ? null : bookedTime.toString();
    }

}
