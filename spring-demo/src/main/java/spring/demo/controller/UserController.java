package spring.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import spring.demo.dto.UserDTO;
import spring.demo.services.UserService;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
	public UserDTO getUserById(@PathVariable("id") int id) {
		return userService.findUserById(id);
	}

	@RequestMapping(value = "/all", method = RequestMethod.GET)
	public List<UserDTO> getAllUsers() {
		return userService.findAll();
	}

	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public int insertUser(@RequestBody UserDTO userDTO) {
		return userService.create(userDTO);
	}
}

