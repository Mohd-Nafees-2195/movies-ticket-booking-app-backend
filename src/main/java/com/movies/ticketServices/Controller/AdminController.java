package com.movies.ticketServices.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movies.ticketServices.Model.Movies;
import com.movies.ticketServices.Model.DTO.ResponceDTO;
import com.movies.ticketServices.Services.MovieServices;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

	@Autowired
	private MovieServices movieServices;

	@GetMapping("/")
	public String adminAccess() {
		return "Hello Admin";
	}
	
	@PostMapping("/insert")
	public ResponceDTO insertNewMovie(@RequestBody Movies newMovie) {
		if(newMovie!=null) {
			return movieServices.insertNewMovie(newMovie);
		}else {
			return new ResponceDTO("Failed!!","Something Went Wrong");
		}
	}
}
