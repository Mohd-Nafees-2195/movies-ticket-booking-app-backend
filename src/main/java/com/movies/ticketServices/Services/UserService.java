package com.movies.ticketServices.Services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.movies.ticketServices.Model.ApplicationUser;
import com.movies.ticketServices.Model.Movies;
import com.movies.ticketServices.Model.Tickets;
import com.movies.ticketServices.Model.Timing;
import com.movies.ticketServices.Model.DTO.ResponceDTO;
import com.movies.ticketServices.Model.DTO.TicketDTO;
import com.movies.ticketServices.Repository.MoviesRepository;
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

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		 System.out.println("In the user details services");

		 return userRepository.findByUsername(username).orElseThrow(()-> new UsernameNotFoundException("User is not Valid"));

	}
	
	//Get All Movies
	public Iterable<Movies> getAllMovies(Integer start, Integer end){
		return moviesRepository.findAll(PageRequest.of(start, end - start + 1)).getContent();
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
				ticketsRepository.save(newTicket);
				return ticket;
			}
			
		}catch(Exception e) {
			ticket.setSeatNumber(-1);
			return ticket;
		}
		
		
		
	}
}
