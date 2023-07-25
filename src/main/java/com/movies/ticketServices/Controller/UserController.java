package com.movies.ticketServices.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.ticketServices.Model.Movies;
import com.movies.ticketServices.Services.MovieServices;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	private MovieServices movieServices;
	
	@GetMapping("/")
	public String userAccess() {
		return "Hello User";
	}
	
	@GetMapping("/movies")
	public Iterable<Movies> getAllMovies() {
		return movieServices.getAllMovies();
	}

}
