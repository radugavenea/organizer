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
import spring.organizer.errorhandler.EntityValidationException;
import spring.organizer.errorhandler.ResourceNotFoundException;
import spring.organizer.services.UserService;
import spring.organizer.tests.config.TestJPAConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestJPAConfiguration.class })
@Transactional
public class UserOldServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void testCreate() {

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

//		userService.create(dto1);
//		userService.create(dto2);

		List<UserDTO> fromDB = userService.findAll();

		assertTrue("One entity inserted", fromDB.size() == 2);
	}
    
//	@Test
//	public void testGetByIdSuccessful() {
//
//        UserDTO dto = new UserDTO.Builder()
//                .name("First")
//                .email("UserOld")
//                .role("Cluj")
//                .create();
//
//		int userId = userService.create(dto);
//		UserDTO fromDB = userService.findUserById(userId);
//
//		assertTrue("Name ", dto.getName().equals(fromDB.getName()));
//		assertTrue("Email ", dto.getEmail().equals(fromDB.getEmail()));
//		assertTrue("Role ", dto.getRole().equals(fromDB.getRole()));
//	}
//
//
//	@Test
//	public void testGetByIdDoubleFirstName() {
//
//		UserDTO dto = new UserDTO.Builder()
//				.name("First First2")
//				.email("user@gmail.com")
//				.role("UserOld")
//				.create();
//
//		int userId = userService.create(dto);
//		UserDTO fromDB = userService.findUserById(userId);
//
//		assertTrue("Name ", dto.getName().equals(fromDB.getName()));
//	}

//
//	@Test(expected = EntityValidationException.class)
//	public void testCreateUnsuccessfulEmail() {
//
//		UserDTO dto = new UserDTO.Builder()
//				.name("First First2")
//				.role("UserOld")
//				.create();
//
//		int userId = userService.create(dto);
//		UserDTO fromDB = userService.findUserById(userId);
//
//		assertTrue("Name ", dto.getName().equals(fromDB.getName()));
//	}
//
//	@Test(expected = EntityValidationException.class)
//	public void testCreateUnsuccessfulSurname() {
//
//		UserDTO dto = new UserDTO.Builder()
//				.name("First First2")
//				.email("user@gmail.com")
//				.create();

//		int userId = userService.create(dto);
//		UserDTO fromDB = userService.findUserById(userId);
//
//		assertTrue("Name ", dto.getName().equals(fromDB.getName()));
//	}
//
//
//	@Test(expected = EntityValidationException.class)
//	public void testCreateUnsuccessfulFirstname() {
//
//		UserDTO dto = new UserDTO.Builder()
//				.role("Surname")
//				.email("user@gmail.com")
//				.create();
//
//		int userId = userService.create(dto);
//		UserDTO fromDB = userService.findUserById(userId);
//
//		assertTrue("Name ", dto.getName().equals(fromDB.getName()));
//	}
//
//	@Test(expected = ResourceNotFoundException.class)
//	public void testGetByIdUnsuccessful() {
//
//        UserDTO dto = new UserDTO.Builder()
//                .name("First")
//				.email("user@gmail.com")
//                .role("UserOld")
//                .create();
//
//		int userId = userService.create(dto);
//		userService.findUserById(userId + 1);
//
//	}

}
