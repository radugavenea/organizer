package spring.organizer.tests.integration;


import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import spring.organizer.errorhandler.EntityValidationException;
import spring.organizer.errorhandler.ResourceNotFoundException;
import spring.organizer.dto.UserDTO;
import spring.organizer.services.UserService;
import spring.organizer.tests.config.TestJPAConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { TestJPAConfiguration.class })
@Transactional
public class UserServiceTest {

	@Autowired
	private UserService userService;

	@Test
	public void testCreate() {

        UserDTO dto1 = new UserDTO.Builder()
                .firstname("First")
                .surname("User")
                .city("Cluj")
                .address("str address")
                .email("user@gmail.com")
                .telephone("085435782483")
                .country("UK")
                .IBAN("5345784396")
                .postcode("859435")
                .create();
        
        UserDTO dto2 = new UserDTO.Builder()
                .firstname("Second")
                .surname("User")
                .city("Cluj")
                .address("str address")
                .email("user@gmail.com")
                .telephone("085435782483")
                .country("UK")
                .IBAN("5345784396")
                .postcode("859435")
                .create();

		userService.create(dto1);
		userService.create(dto2);

		List<UserDTO> fromDB = userService.findAll();

		assertTrue("One entity inserted", fromDB.size() == 2);
	}
    
	@Test
	public void testGetByIdSuccessful() {

        UserDTO dto = new UserDTO.Builder()
                .firstname("First")
                .surname("User")
                .city("Cluj")
                .address("str address")
                .email("user@gmail.com")
                .telephone("085435782483")
                .country("UK")
                .IBAN("5345784396")
                .postcode("859435")
                .create();

		int userId = userService.create(dto);
		UserDTO fromDB = userService.findUserById(userId);

		assertTrue("First Name ", dto.getFirstname().equals(fromDB.getFirstname()));
		assertTrue("Sur Name ", dto.getSurname().equals(fromDB.getSurname()));
		assertTrue("City ", dto.getCity().equals(fromDB.getCity()));
		assertTrue("Address", dto.getAddress().equals(fromDB.getAddress()));
		assertTrue("Email ", dto.getEmail().equals(fromDB.getEmail()));
		assertTrue("Telephone", dto.getTelephone().equals(fromDB.getTelephone()));
		assertTrue("Country ", dto.getCountry().equals(fromDB.getCountry()));
		assertTrue("IBAN ", !dto.getIBAN().equals(fromDB.getIBAN()));
		assertTrue("Empty IBAN ", fromDB.getIBAN() == null);
		assertTrue("Postcode ", dto.getPostcode().equals(fromDB.getPostcode()));
	}


	@Test
	public void testGetByIdDoubleFirstName() {

		UserDTO dto = new UserDTO.Builder()
				.firstname("First First2")
				.surname("User")
				.email("user@gmail.com")
				.create();

		int userId = userService.create(dto);
		UserDTO fromDB = userService.findUserById(userId);

		assertTrue("First Name ", dto.getFirstname().equals(fromDB.getFirstname()));
		assertTrue("Sur Name ", dto.getSurname().equals(fromDB.getSurname()));
	}


	@Test(expected = EntityValidationException.class)
	public void testCreateUnsuccessfulEmail() {

		UserDTO dto = new UserDTO.Builder()
				.firstname("First First2")
				.surname("User")
				.create();

		int userId = userService.create(dto);
		UserDTO fromDB = userService.findUserById(userId);

		assertTrue("First Name ", dto.getFirstname().equals(fromDB.getFirstname()));
		assertTrue("Sur Name ", dto.getSurname().equals(fromDB.getSurname()));
	}

	@Test(expected = EntityValidationException.class)
	public void testCreateUnsuccessfulSurname() {

		UserDTO dto = new UserDTO.Builder()
				.firstname("First First2")
				.email("user@gmail.com")
				.create();

		int userId = userService.create(dto);
		UserDTO fromDB = userService.findUserById(userId);

		assertTrue("First Name ", dto.getFirstname().equals(fromDB.getFirstname()));
		assertTrue("Sur Name ", dto.getSurname().equals(fromDB.getSurname()));
	}


	@Test(expected = EntityValidationException.class)
	public void testCreateUnsuccessfulFirstname() {

		UserDTO dto = new UserDTO.Builder()
				.surname("Surname")
				.email("user@gmail.com")
				.create();

		int userId = userService.create(dto);
		UserDTO fromDB = userService.findUserById(userId);

		assertTrue("First Name ", dto.getFirstname().equals(fromDB.getFirstname()));
		assertTrue("Sur Name ", dto.getSurname().equals(fromDB.getSurname()));
	}

	@Test(expected = ResourceNotFoundException.class)
	public void testGetByIdUnsuccessful() {

        UserDTO dto = new UserDTO.Builder()
                .firstname("First")
                .surname("User")
                .city("Cluj")
                .address("str address")
                .email("user@gmail.com")
                .telephone("085435782483")
                .country("UK")
                .IBAN("5345784396")
                .postcode("859435")
                .create();

		int userId = userService.create(dto);
		userService.findUserById(userId + 1);

	}

}
