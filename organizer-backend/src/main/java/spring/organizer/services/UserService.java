package spring.organizer.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import spring.organizer.errorhandler.EntityValidationException;
import spring.organizer.errorhandler.ResourceNotFoundException;
import spring.organizer.dto.UserDTO;
import spring.organizer.entities.User;
import spring.organizer.repositories.UserRepository;

@Service
public class UserService {
	private static final String SPLIT_CH = " ";
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	@Autowired
	private UserRepository usrRepository;

	public UserDTO findUserById(int userId) {
		User usr = usrRepository.findById(userId);
		if (usr == null) {
			throw new ResourceNotFoundException(User.class.getSimpleName());
		}
		String[] names = extractNames(usr.getName());

		UserDTO dto = new UserDTO.Builder()
						.id(usr.getId())
						.firstname(names[0])
						.surname(names[1])
						.city(usr.getCity())
						.address(usr.getAddress())
						.email(usr.getEmail())
						.telephone(usr.getTelephone())
						.country(usr.getCountry())
						.postcode(usr.getPostcode())
						.create();
		return dto;
	}
	
	public List<UserDTO> findAll() {
		List<User> users = usrRepository.findAll();
		List<UserDTO> toReturn = new ArrayList<UserDTO>();
		for (User user : users) {
			String[] names = extractNames(user.getName());
			UserDTO dto = new UserDTO.Builder()
						.id(user.getId())
						.firstname(names[0])
						.surname(names[1])
						.telephone(user.getTelephone())
						.create();
			toReturn.add(dto);
		}
		return toReturn;
	}

	public int create(UserDTO userDTO) {
		List<String> validationErrors = validateUser(userDTO);
		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(User.class.getSimpleName(),validationErrors);
		}

		User user = new User();
		user.setName(userDTO.getFirstname().trim() + SPLIT_CH + userDTO.getSurname().trim());
		user.setEmail(userDTO.getEmail());
		user.setAddress(userDTO.getAddress());
		user.setPostcode(userDTO.getPostcode());
		user.setCity(userDTO.getCity());
		user.setCountry(userDTO.getCountry());
		user.setTelephone(userDTO.getTelephone());
		user.setIBAN(userDTO.getIBAN());
		user.setCreated(new Date());
		


		User usr = usrRepository.save(user);
		return usr.getId();
	}

	private List<String> validateUser(UserDTO usr) {
		List<String> validationErrors = new ArrayList<String>();

		if (usr.getFirstname() == null || "".equals(usr.getFirstname())) {
			validationErrors.add("First Name field should not be empty");
		}

		if (usr.getSurname() == null || "".equals(usr.getSurname())) {
			validationErrors.add("Surname field should not be empty");
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
	private String[] extractNames(String fullname){
		String[] names = new String[2];
		int surnameIndex = fullname.lastIndexOf(SPLIT_CH);
		names[0] = fullname;
		names[1] = "";
		if (surnameIndex != -1) {
			names[0] = fullname.substring(0, surnameIndex).trim();
			names[1] = fullname.substring(surnameIndex).trim();
		}
		return names;
	}
}
