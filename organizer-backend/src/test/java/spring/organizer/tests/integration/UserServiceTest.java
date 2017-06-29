package spring.organizer.tests.integration;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import spring.organizer.dto.UserDTO;
import spring.organizer.entities.User;
import spring.organizer.errorhandler.EntityValidationException;
import spring.organizer.errorhandler.ResourceNotFoundException;
import spring.organizer.services.UserService;
import spring.organizer.tests.config.TestJPAConfiguration;

/**
 * Created by radu on 29.06.2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestJPAConfiguration.class })
@Transactional
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void insertUserTest() {

        UserDTO dto1 = new UserDTO.Builder()
                .name("First")
                .email("UserOld")
                .role("Cluj")
                .create();
        
        UserDTO dto2 = new UserDTO.Builder()
                .name("Second")
                .email("UserOld")
                .role("Cluj")
                .create();

        User user1 = new User(null,"nume","email@email.com","email","user");
        User user2 = new User(null,"gogu","gogu@gmail.com","gogu","user");


		userService.insertOrUpdate(user1);
		userService.insertOrUpdate(user2);

		List<UserDTO> fromDB = userService.findAll();

		assertTrue("One entity inserted", fromDB.size() == 2);
	}

	@Test
	public void getUserByIdSuccessfulTest() {

		User user1 = new User(null,"nume","email@email.com","email","user");

		int userId = userService.insertOrUpdate(user1);
		UserDTO fromDB = userService.findUserById(userId);

		assertTrue("Name ", user1.getName().equals(fromDB.getName()));
		assertTrue("Email ", user1.getEmail().equals(fromDB.getEmail()));
		assertTrue("Role ", user1.getRole().equals(fromDB.getRole()));
	}


	@Test
	public void getUserByIdNameTest() {

		User user1 = new User(null,"nume","email@email.com","email","user");

		userService.insertOrUpdate(user1);
		UserDTO fromDB = userService.findUserByEmail(user1.getEmail());

		assertTrue("Name ", user1.getName().equals(fromDB.getName()));
	}


	@Test(expected = EntityValidationException.class)
	public void insertUnsuccessfulEmailTest() {

		User user1 = new User(null,"nume","email@emailcom","email","user");

		int userId = userService.insertOrUpdate(user1);
		UserDTO fromDB = userService.findUserById(userId);

		assertTrue("Name ", user1.getName().equals(fromDB.getName()));
	}


	@Test(expected = EntityValidationException.class)
	public void insertUnsuccessfulNameTest() {

		User user1 = new User(null,"","email@email.com","email","user");

		int userId = userService.insertOrUpdate(user1);
		UserDTO fromDB = userService.findUserById(userId);

		assertTrue("Name ", user1.getName().equals(fromDB.getName()));
	}

	@Test(expected = ResourceNotFoundException.class)
	public void getByIdUnsuccessfulTest() {

		User user1 = new User(null,"nume","email@email.com","email","user");

		int userId = userService.insertOrUpdate(user1);
		userService.findUserById(userId + 1);
	}

}
