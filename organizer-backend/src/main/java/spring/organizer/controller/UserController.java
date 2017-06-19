package spring.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import spring.organizer.dto.UserDTO;
import spring.organizer.services.UserService;

import java.util.List;

/**
 * Created by radu on 19.06.2017.
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value= "/all", method = RequestMethod.GET)
    public List<UserDTO> getAllUsers() {
        return userService.findAll();
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public UserDTO getUserById(@PathVariable("id") int id){
        return userService.findUserById(id);
    }

    @RequestMapping(value = "/insert", method = RequestMethod.POST)
    public int insertUser(@RequestBody UserDTO userDTO) {
        return userService.create(userDTO);
    }
}
