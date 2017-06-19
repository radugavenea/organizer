package spring.organizer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.organizer.dto.UserDTO;
import spring.organizer.entities.User;
import spring.organizer.errorhandler.ResourceNotFoundException;
import spring.organizer.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by radu on 19.06.2017.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserDTO findUserById(int id){
        User user = userRepository.findById(id);
        if(user == null) {
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

    public int create(UserDTO userDTO) {
        int id = 0;

        // to be modified

        return id;
    }
}
