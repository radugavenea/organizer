package spring.organizer.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by radu on 19.06.2017.
 */
@Entity
@Table(name = "event")
public class Event implements Serializable{

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private Date startDate;
    private Date endDate;
    private Date remainderDate;
    private String note;
    private Integer archived;
    private Integer deleted;
    private Integer goalId;

    public Event() {
    }

    public Event(Integer id, String name, Date startDate, Date endDate, Date remainderDate, String note, Integer archived, Integer deleted, Integer goalId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.remainderDate = remainderDate;
        this.note = note;
        this.archived = archived;
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

    @Column(name = "name", length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "start_date")
    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Column(name = "end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name = "remainder_date")
    public Date getRemainderDate() {
        return remainderDate;
    }

    public void setRemainderDate(Date remainderDate) {
        this.remainderDate = remainderDate;
    }

    @Column(name = "note", length = 255)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Column(name = "archived")
    public Integer getArchived() {
        return archived;
    }

    public void setArchived(Integer archived) {
        this.archived = archived;
    }

    @Column(name = "deleted")
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Column(name = "goal_id")
    public Integer getGoalId() {
        return goalId;
    }

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }
}
