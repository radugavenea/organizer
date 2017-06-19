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

@Service
public class UserServiceOld {/*
	private static final String SPLIT_CH = " ";
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	@Autowired
	private UserRepository usrRepository;

	public UserDTO findUserById(int userId) {
		UserOld usr = usrRepository.findById(userId);
		if (usr == null) {
			throw new ResourceNotFoundException(UserOld.class.getSimpleName());
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
		List<UserOld> userOlds = usrRepository.findAll();
		List<UserDTO> toReturn = new ArrayList<UserDTO>();
		for (UserOld userOld : userOlds) {
			String[] names = extractNames(userOld.getName());
			UserDTO dto = new UserDTO.Builder()
						.id(userOld.getId())
						.firstname(names[0])
						.surname(names[1])
						.telephone(userOld.getTelephone())
						.create();
			toReturn.add(dto);
		}
		return toReturn;
	}

	public int create(UserDTO userDTO) {
		List<String> validationErrors = validateUser(userDTO);
		if (!validationErrors.isEmpty()) {
			throw new EntityValidationException(UserOld.class.getSimpleName(),validationErrors);
		}

		UserOld userOld = new UserOld();
		userOld.setName(userDTO.getFirstname().trim() + SPLIT_CH + userDTO.getSurname().trim());
		userOld.setEmail(userDTO.getEmail());
		userOld.setAddress(userDTO.getAddress());
		userOld.setPostcode(userDTO.getPostcode());
		userOld.setCity(userDTO.getCity());
		userOld.setCountry(userDTO.getCountry());
		userOld.setTelephone(userDTO.getTelephone());
		userOld.setIBAN(userDTO.getIBAN());
		userOld.setCreated(new Date());
		


		UserOld usr = usrRepository.save(userOld);
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
	}*/
}
