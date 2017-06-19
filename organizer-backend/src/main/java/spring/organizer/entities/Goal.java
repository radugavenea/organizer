package spring.organizer.entities;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by radu on 19.06.2017.
 */
@Entity
@Table(name = "goal")
public class Goal implements Serializable{

    private static final long serialVersionUID = 1L;
    private Integer id;
    private String name;
    private String description;
    private String actionPlan;
    private String progress;
    private String example;
    private Integer deleted;
    private Integer userId;

    public Goal() {
    }

    public Goal(Integer id, String name, String description, String actionPlan, String progress, String example, Integer deleted, Integer userId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.actionPlan = actionPlan;
        this.progress = progress;
        this.example = example;
        this.deleted = deleted;
        this.userId = userId;
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

    @Column(name = "description", length = 1024)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "action_plan", length = 1024)
    public String getActionPlan() {
        return actionPlan;
    }

    public void setActionPlan(String actionPlan) {
        this.actionPlan = actionPlan;
    }

    @Column(name = "progress", length = 1024)
    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    @Column(name = "example", length = 1024)
    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    @Column(name = "deleted")
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
