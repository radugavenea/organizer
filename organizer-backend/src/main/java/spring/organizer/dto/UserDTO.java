package spring.organizer.dto;

/**
 * Created by radu on 19.06.2017.
 */
public class UserDTO {

    private Integer id;
    private String name;
    private String email;
    private String role;

    public UserDTO() {
    }

    public UserDTO(Integer id, String name, String email, String role) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }



    public static class Builder{

        private Integer nestedid;
        private String nestedname;
        private String nestedemail;
        private String nestedrole;

        public Builder id(int id){
            this.nestedid = id;
            return this;
        }

        public Builder name(String name){
            this.nestedname = name;
            return this;
        }

        public Builder email(String email){
            this.nestedemail = email;
            return this;
        }

        public Builder role(String role){
            this.nestedrole = role;
            return this;
        }

        public UserDTO create(){
            return new UserDTO(nestedid, nestedname, nestedemail, nestedrole);
        }

    }
}
