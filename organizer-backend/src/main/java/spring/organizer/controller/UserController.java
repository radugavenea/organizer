package spring.organizer.controller;

import com.sun.deploy.net.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.organizer.dto.UserDTO;
import spring.organizer.entities.User;
import spring.organizer.errorhandler.ResourceNotFoundException;
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


    @RequestMapping(value = "/", method = RequestMethod.POST)
    public int insertUser(@RequestBody User user) {
        return userService.saveOrUpdate(user);
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") int id, @RequestBody User user){
        UserDTO userDTO;
        try{
            userDTO = userService.findUserById(id);
        }
        catch (ResourceNotFoundException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        userService.saveOrUpdate(user);
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<User> deleteUser(@PathVariable("id") int id){
        try{
            userService.deleteUserById(id);
        }catch (ResourceNotFoundException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
