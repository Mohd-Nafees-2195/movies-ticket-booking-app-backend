package com.movies.ticketServices.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movies.ticketServices.Model.ApplicationUser;
import com.movies.ticketServices.Model.Movies;
import com.movies.ticketServices.Model.Theatres;
import com.movies.ticketServices.Model.Tickets;
import com.movies.ticketServices.Model.Timing;
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
	
	@GetMapping("/get-movie")
	public Movies getMovie(@RequestParam Integer id) {
		return userService.getMovie(id);
	}
	
	@GetMapping("/get-timing")
	public Iterable<Timing> getTiming(@RequestParam Integer id) {
		return userService.getTiming(id);
	}
	
	@PostMapping("/create-ticket")
	public TicketDTO createTicket(@RequestBody TicketDTO ticket) {
		if(ticket.getEmail()==null||ticket.getMovieId()==null||ticket.getTimeId()==null) {
			return new TicketDTO();//Field missing
		}else {
			return userService.createTicket(ticket);
		}
	}
	
	@GetMapping("/get-ticket")
	public Tickets getTicket(@RequestParam Integer id) {
		System.out.println("id = "+id);
		return userService.getTicket(id);
	}
	
	@GetMapping("/get-all-tickets")
	public Iterable<Tickets> getAllTickets(@RequestParam("id") Integer userId){
		if(userId<=0 || userId==null)
			return null;
		return userService.getAllTickets(userId);
	}
	
	@GetMapping("/get-theater")
	public Theatres getTheater(@RequestParam Integer id) {
		return userService.getTheater(id);
	}
	
	@GetMapping("/get-searched-movies")
	public Iterable<Movies> searchByTitleLike(@RequestParam("title") String title){
		if(title==null||title=="") {
			System.out.println("dhjkfs jdnids dijoisd ");
			return null;
		}
		return userService.searchByTitleLike(title);
	}
	
	@GetMapping("/get-user")
	public ApplicationUser getUser(@RequestParam("email") String email) {
		return userService.getUser(email);
	}
}
