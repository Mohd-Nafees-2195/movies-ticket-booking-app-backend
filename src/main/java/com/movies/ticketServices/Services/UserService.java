package com.movies.ticketServices.Services;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.movies.ticketServices.Model.ApplicationUser;
import com.movies.ticketServices.Model.Movies;
import com.movies.ticketServices.Model.Theatres;
import com.movies.ticketServices.Model.Tickets;
import com.movies.ticketServices.Model.Timing;
import com.movies.ticketServices.Model.DTO.ResponceDTO;
import com.movies.ticketServices.Model.DTO.TicketDTO;
import com.movies.ticketServices.Repository.MoviesRepository;
import com.movies.ticketServices.Repository.TheatresRepository;
import com.movies.ticketServices.Repository.TicketsRepository;
import com.movies.ticketServices.Repository.TimingRepository;
import com.movies.ticketServices.Repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MoviesRepository moviesRepository;
	
	@Autowired
	private TimingRepository timeRepository;
	
	@Autowired
	private TicketsRepository ticketsRepository;
	
	@Autowired
	private TheatresRepository theatresRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 System.out.println("In the user details services");

		 return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User is not Valid"));

	}
	
	//Get All Movies
	//@Query("")
	public Iterable<Movies>  getAllMovies(Integer start, Integer end){
		//return moviesRepository.findAll(PageRequest.of(start, end)).getContent();
		return moviesRepository.findAll();
	}
	
	//Get Particular movie by id
	public Movies getMovie(Integer movieId) {
		if(movieId==null)
			 return null;
		try {
			return moviesRepository.findById(movieId).get();
		}catch(NoSuchElementException e) {
			return null;
		}
		
	}
	
	
	public Iterable<Timing> getTiming(Integer movieId) {
		if(movieId==null)
			 return null;
		try {
			return timeRepository.findAllByMovieId(movieId);
		}catch(NoSuchElementException e) {
			return null;
		}
	}
	
	//Create Ticket
	@Transactional
	public TicketDTO createTicket(TicketDTO ticket) {
		try {
			
			ApplicationUser user=userRepository.findByEmail(ticket.getEmail()).get();
			if(user==null) {
				return new TicketDTO();//return invalid user
			}else {
				Timing time=timeRepository.findById(ticket.getTimeId()).get();
				if(time.getAvailableSeats()<=0) {
					ticket.setSeatNumber(-1);
					return ticket;
				}
				time.setAvailableSeats(time.getAvailableSeats()-1);
				time.setReserveSeats(time.getReserveSeats()+1);
				
				Tickets newTicket=new Tickets(0,time.getDate(),time.getStartTime(),time.getEndTime(),time.getReserveSeats(),time.getMovieId(),user.getUserId());
				ticket.setSeatNumber(time.getReserveSeats());
				ticket.setDate(time.getDate());
				ticket.setStartTime(time.getStartTime());
				ticket.setEndTime(time.getEndTime());
				
				timeRepository.save(time);
				Tickets storedTicket=ticketsRepository.save(newTicket);
				ticket.setTicketId(storedTicket.getTicketId());
				return ticket;
			}
			
		}catch(Exception e) {
			ticket.setSeatNumber(-1);
			return ticket;
		}
		
	}
	
	//Get Ticket
	public Tickets getTicket(Integer ticketId) {
		if(ticketId==null)
			 return null;
		try {
			return ticketsRepository.findById(ticketId).get();
		}catch(NoSuchElementException e) {
			return null;
		}
		
	}
	
	//Get All Tickets
	public Iterable<Tickets> getAllTickets(Integer userId){
		try {
			return ticketsRepository.findAllByUserId(userId);
		}catch(Exception e) {
			return null;
		}
	}
	
	//get Theater
	public Theatres getTheater(Integer theaterId) {
		if(theaterId==null)
			return null;
		try {
			return theatresRepository.findById(theaterId).get();
		}catch(Exception e) {
			return null;
		}
	}
	
	//Get search movie
	@Transactional
	public Iterable<Movies> searchByTitleLike(String title){
		return moviesRepository.searchByTitleLike(title);
	}
	
	public ApplicationUser getUser(String email) {
		return userRepository.findByEmail(email).get();
	}
}
