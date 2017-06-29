package spring.organizer.dto;

import java.time.Duration;

/**
 * Created by radu on 27.06.2017.
 */
public class GoalDTO {

    private Integer id;
    private String name;
    private String description;
    private String actionPlan;
    private String progress;
    private String example;
    private Integer userId;
    private String totalBudget;
    private String availableBudget;

    public GoalDTO() {
    }

    public GoalDTO(Integer id, String name, String description, String actionPlan, String progress,
                   String example, Integer userId, String totalBudget, String availableBudget) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.actionPlan = actionPlan;
        this.progress = progress;
        this.example = example;
        this.userId = userId;
        this.totalBudget = totalBudget;
        this.availableBudget = availableBudget;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActionPlan() {
        return actionPlan;
    }

    public void setActionPlan(String actionPlan) {
        this.actionPlan = actionPlan;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTotalBudget() {
        return totalBudget;
    }

    public void setTotalBudget(String totalBudget) {
        this.totalBudget = totalBudget;
    }

    public String getBookedBudget() {
        return availableBudget;
    }

    public void setBookedBudget(String availableBudget) {
        this.availableBudget = availableBudget;
    }

    public static class Builder{

        private Integer nestedid;
        private String nestedname;
        private String nesteddescription;
        private String nestedactionPlan;
        private String nestedprogress;
        private String nestedexample;
        private Integer nesteduserId;
        private Duration nestedtotalBudget;
        private Duration nestedavailableBudget;

        public Builder id(int id){
            this.nestedid = id;
            return this;
        }
        public Builder name(String name){
            this.nestedname = name;
            return this;
        }
        public Builder description(String description){
            this.nesteddescription = description;
            return this;
        }
        public Builder actionPlan(String actionPlan){
            this.nestedactionPlan = actionPlan;
            return this;
        }
        public Builder progress(String progress){
            this.nestedprogress = progress;
            return this;
        }
        public Builder example(String example){
            this.nestedexample = example;
            return this;
        }
        public Builder userId(Integer userId){
            this.nesteduserId = userId;
            return this;
        }
        public Builder totalBudget(Duration totalBudget){
            this.nestedtotalBudget = totalBudget;
            return this;
        }
        public Builder availableBudget(Duration availableBudget){
            this.nestedavailableBudget = availableBudget;
            return this;
        }

        public GoalDTO create() {
            return new GoalDTO(nestedid,nestedname,nesteddescription,nestedactionPlan,nestedprogress,
                    nestedexample,nesteduserId,
                    Long.toString(nestedtotalBudget.toHours()),
                    Long.toString(nestedavailableBudget.toHours()));
        }
    }
}
