package spring.organizer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.organizer.dto.UserDTO;
import spring.organizer.entities.User;
import spring.organizer.errorhandler.EntityValidationException;
import spring.organizer.errorhandler.ResourceNotFoundException;
import spring.organizer.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by radu on 19.06.2017.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        List<UserDTO> toReturn = new ArrayList<UserDTO>();
        for (User user : users) {
            UserDTO dto = new UserDTO.Builder()
                    .id(user.getId())
                    .name(user.getName())
                    .email(user.getEmail())
                    .role(user.getRole())
                    .create();
            toReturn.add(dto);
        }
        return toReturn;
    }

    public UserDTO findUserById(int id){
        User user = userRepository.findById(id);
        if(user == null) {
            throw new ResourceNotFoundException(User.class.getSimpleName());
        }

        UserDTO userDTO = new UserDTO.Builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .role(user.getRole())
                .create();
        return userDTO;
    }

    public UserDTO findUserByEmail(String email){
        User user = userRepository.findByEmail(email);
        if(user == null){
            throw new ResourceNotFoundException(User.class.getSimpleName());
        }
        UserDTO userDTO = new UserDTO.Builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .role(user.getRole())
                .create();
        return userDTO;
    }

    public int insertOrUpdate(User user) {
        List<String> validationErrors = validateUser(user);
        if (!validationErrors.isEmpty()) {
            throw new EntityValidationException(User.class.getSimpleName(),validationErrors);
        }
        userRepository.save(user);
        return user.getId();
    }

    public int deleteUserById(int id) {
        userRepository.delete(id);
        return id;
    }



    private List<String> validateUser(User usr) {
        List<String> validationErrors = new ArrayList<String>();

        if (usr.getName() == null || "".equals(usr.getName())) {
            validationErrors.add("Name field should not be empty");
        }

        if (usr.getEmail() == null || "".equals(usr.getEmail())) {
            validationErrors.add("Email field should not be empty");
        }

        if (usr.getEmail() == null || !validateEmail(usr.getEmail())) {
            validationErrors.add("Email does not have the correct format.");
        }

        return validationErrors;
    }

    public static boolean validateEmail(String email) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(email);
        return matcher.find();
    }

}
