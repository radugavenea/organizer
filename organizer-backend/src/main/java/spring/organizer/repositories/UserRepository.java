package spring.organizer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import spring.organizer.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByName(String name);

	User findById(int id);

}
