package spring.organizer.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by radu on 28.06.2017.
 */
public class EventDTO {


    private Integer id;
    private String name;
    private String startDate;
    private String endDate;
    private String remainderDate;
    private String note;
    private Integer goalId;

    public EventDTO() {
    }

    public EventDTO(Integer id, String name, String startDate, String endDate, String remainderDate,
                    String note, Integer goalId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.remainderDate = remainderDate;
        this.note = note;
        this.goalId = goalId;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getRemainderDate() {
        return remainderDate;
    }

    public void setRemainderDate(String remainderDate) {
        this.remainderDate = remainderDate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Integer getGoalId() {
        return goalId;
    }

    public void setGoalId(Integer goalId) {
        this.goalId = goalId;
    }

    public static class Builder{

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        private DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        private Integer nestedid;
        private String nestedname;
        private LocalDateTime nestedstartDate;
        private LocalDateTime nestedendDate;
        private LocalDateTime nestedremainderDate;
        private String nestednote;
        private Integer nestedgoalId;

        public Builder id(int id){
            this.nestedid = id;
            return this;
        }

        public Builder name(String name){
            this.nestedname = name;
            return this;
        }

        public Builder startDate(LocalDateTime date){
            this.nestedstartDate = date;
            return this;
        }

        public Builder endDate(LocalDateTime date){
            this.nestedendDate = date;
            return this;
        }

        public Builder remainderDate(LocalDateTime date){
            this.nestedremainderDate = date;
            return this;
        }

        public Builder note(String note){
            this.nestednote = note;
            return this;
        }

        public Builder goalId(Integer id){
            this.nestedgoalId = id;
            return this;
        }

        public EventDTO create(){
            return new EventDTO(nestedid,nestedname,
                    nestedstartDate.format(format),
                    nestedendDate.format(format),
                    nestedremainderDate.format(format),
                    nestednote, nestedgoalId);
        }

    }
}
