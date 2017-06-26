package spring.organizer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import spring.organizer.entities.User;
import spring.organizer.services.LoginService;

import javax.servlet.http.HttpSession;

/**
 * Created by radu on 24.06.2017.
 */
@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "", method = RequestMethod.POST)
    public User verifyLogin(@RequestBody User user, HttpSession session, Model model){
        User lUser = loginService.loginUser(user.getEmail(),user.getPassword());
        if(lUser == null){
            model.addAttribute("loginError","Wrong credentials. Please try again!");
        }
        session.setAttribute("loggedInUser",lUser);
        return lUser;
    }

}
