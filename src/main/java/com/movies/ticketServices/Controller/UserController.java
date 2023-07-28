package com.movies.ticketServices.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movies.ticketServices.Model.Movies;
import com.movies.ticketServices.Model.DTO.TicketDTO;
import com.movies.ticketServices.Services.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {

	@Autowired
	UserService userService;

	@GetMapping("/")
	public String userAccess() {
		return "Hello User";
	}

	@GetMapping("/get-all-movie")
	public Iterable<Movies> getAllMovies(@RequestParam Integer start,@RequestParam Integer end) {
		return userService.getAllMovies(start,end);
	}
	
	@PostMapping("/create-ticket")
	public TicketDTO createTicket(@RequestBody TicketDTO ticket) {
		if(ticket.getEmail()==null||ticket.getMovieId()==null||ticket.getTimeId()==null) {
			return new TicketDTO();//Field missing
		}else {
			return userService.createTicket(ticket);
		}
	}
}
