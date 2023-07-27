package com.movies.ticketServices.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.ticketServices.Model.Movies;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {


	@GetMapping("/")
	public String userAccess() {
		return "Hello User";
	}


}
