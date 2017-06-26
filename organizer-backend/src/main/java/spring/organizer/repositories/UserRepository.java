package spring.organizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.organizer.dto.UserDTO;
import spring.organizer.entities.User;

/**
 * Created by radu on 19.06.2017.
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findById(int id);
    User findByEmail(String email);
    User save(User s);
//    User findOne(Integer integer);
    void delete(Integer id);
}
