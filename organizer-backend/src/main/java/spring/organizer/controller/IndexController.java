package spring.organizer.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(maxAge = 3600)
@RestController
public class IndexController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String start() {
		return "Welcome to Spring Demo!";
	}

}
