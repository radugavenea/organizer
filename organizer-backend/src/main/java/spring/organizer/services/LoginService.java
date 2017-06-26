package spring.organizer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import spring.organizer.entities.User;
import spring.organizer.repositories.UserRepository;

/**
 * Created by radu on 24.06.2017.
 */
@Service
public class LoginService {

    @Autowired
    private UserRepository userRepository;

    public User loginUser(String username, String password) {
        User user = userRepository.findByEmail(username);

        if(user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
}
