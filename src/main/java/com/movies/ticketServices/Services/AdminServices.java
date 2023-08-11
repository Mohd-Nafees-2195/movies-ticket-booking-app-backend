package com.movies.ticketServices.Services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import com.movies.ticketServices.Model.ApplicationAdmin;
import com.movies.ticketServices.Model.ApplicationUser;
import com.movies.ticketServices.Model.Movies;
import com.movies.ticketServices.Model.Theatres;
import com.movies.ticketServices.Model.Timing;
import com.movies.ticketServices.Model.DTO.MoviesDTO;
import com.movies.ticketServices.Model.DTO.ResponceDTO;
import com.movies.ticketServices.Model.DTO.TheatreDTO;
import com.movies.ticketServices.Repository.AdminRepository;
import com.movies.ticketServices.Repository.MoviesRepository;
import com.movies.ticketServices.Repository.TheatresRepository;
import com.movies.ticketServices.Repository.TimingRepository;
import com.movies.ticketServices.Repository.UserRepository;

@Service
public class AdminServices  {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private MoviesRepository moviesRepository;

	@Autowired
	private TheatresRepository theatresRepository;

	@Autowired
	private TimingRepository timingRepository;
	
	//Adding new Theater
	public ResponceDTO insertNewTheatre(TheatreDTO theater) {
		try {
			ApplicationUser admin=userRepository.findByEmail(theater.getEmail()).get();
			//Theaters theater1=theatresRepository.findById(admin.getUserId()).get();
			if(admin!=null) {
				System.out.println(theater.getEmail()+"  "+theater.getTheatreCity()+"  "+theater.getTotalSeats()+" "+theater.getTheatreName());
				Theatres newTheatre=new Theatres(0,theater.getTheatreName(),theater.getTheatreCity(),theater.getTotalSeats(),admin.getUserId());
				theatresRepository.save(newTheatre);
				return new ResponceDTO("Success!!","Theatre Added Successfully");
			}else {
				return new ResponceDTO("Failed!!","Invalid Access");
			}
			
		}catch(Exception e) {
			return new ResponceDTO("Failed!!",e.getMessage());
		}
	}
	
	//Insert new Movie
	public ResponceDTO insertNewMovie(MoviesDTO newMovie,byte[] imageData) {
			try {
				//newMovie.setImageData(file);
				
				
				ApplicationUser admin=userRepository.findByEmail(newMovie.getEmail()).get();
				if(admin==null)
					return new ResponceDTO("Failed!!","wrong email");
				Theatres theater=theatresRepository.findById(newMovie.getTheaterId()).get();
				if(theater!=null) {
					
					Movies movie=new Movies(0,newMovie.getMovieName(),newMovie.getMovieTitle(),theater.getTheatreId(),newMovie.getPrice(),imageData);
					Movies movieResponse=moviesRepository.save(movie);
					if(movieResponse!=null) {
					
						System.out.println(newMovie.getMovieName()+" /// "+newMovie.getMovieTitle()+" "+theater.getTheatrename()+" "+movieResponse.getMovieId());	
						
				     //Insert the data of timing table here	
					//Morning Slot
					String timeFormat = "HH:mm";
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern(timeFormat);
					LocalDate date=LocalDate.now();
					
					String startTime1 = "08:00"; 
					String endTime1= "12:00";
					LocalTime startTime=LocalTime.parse(startTime1, formatter);
					LocalTime endTime=LocalTime.parse(endTime1, formatter);
					
					Timing morning=new Timing(0,date,startTime,endTime,theater.getTotalSeats(),0,movieResponse.getMovieId());
					timingRepository.save(morning);
					//Noon slot
					String startTime2 = "13:00"; 
					String endTime2 = "17:00";
					LocalTime startTime22=LocalTime.parse(startTime2, formatter);
					LocalTime endTime22=LocalTime.parse(endTime2, formatter);
					
					Timing noon=new Timing(0,date,startTime22,endTime22,theater.getTotalSeats(),0,movieResponse.getMovieId());
					timingRepository.save(noon);
					//Evening slot
					String startTime3 = "18:00"; 
					String endTime3 = "22:00";
					LocalTime startTime33=LocalTime.parse(startTime3, formatter);
					LocalTime endTime33=LocalTime.parse(endTime3, formatter);
					
					Timing evening=new Timing(0,date,startTime33,endTime33,theater.getTotalSeats(),0,movieResponse.getMovieId());
					timingRepository.save(evening);
						
					return new ResponceDTO("Success!!","New Movie Added Successfully");
						//Timing timing=new Timing(0,);
					}else {
						return new ResponceDTO("Failed!!","Failed to add movie");
					}
					
				}else {
					return new ResponceDTO("Failed!!","Failed to add movie");
				}
				
			}catch(Exception e) {
				return new ResponceDTO("Failed!!","Something Went Wrong");
			}
		}
	
	
	//Get All Movies
	 @Transactional
	public Iterable<Movies> getAllMovies(Integer theaterId){
		System.out.println("Admin Controller"+theaterId);
		return moviesRepository.findAllByTheaterId(theaterId);
	}
	 
	//Get all theater
	public Iterable<Theatres> getAllTheaters(Integer adminId){
		return theatresRepository.getAllTheaterById(adminId);
	}
	
	//Delete Movie
	@Transactional
	public ResponceDTO deleteMovie(Integer movieId) {
		try {
			timingRepository.deleteByMovieId(movieId);
			System.out.println("After Deleting from time table");
			moviesRepository.deleteById(movieId);
			return new ResponceDTO("Success!!","Movie Deleted Successfully");
		}catch(Exception e) {
			return new ResponceDTO("Failed!!",e.getMessage());
		}
	}
	
	//delete Theater
	public ResponceDTO deleteTheater(Integer theaterId) {
		try {
			theatresRepository.deleteById(theaterId);
			return new ResponceDTO("Success!!","Theater Deleted Successfully");
		}catch(Exception e) {
			return new ResponceDTO("Failed!!",e.getMessage());
		}
	}
	
	//Get Single Theater
	public Theatres getTheater(Integer theaterId) {
		try {
			return theatresRepository.findById(theaterId).get();
		}catch(Exception e) {
			return null;
		}
	}
	
	//Update Theater
	public ResponceDTO updateTheater(Integer theaterId,TheatreDTO theater) {
		try {
			
			Theatres data=theatresRepository.findById(theaterId).get();
			if(data!=null) {
				data.setTheatreCity(theater.getTheatreCity());
				data.setTheatrename(theater.getTheatreName());
				data.setTotalSeats(theater.getTotalSeats());
				theatresRepository.save(data);
				return new ResponceDTO("Success!!","Theater Updated Successfully");
			}else {
				return new ResponceDTO("Failed!!","Something went wrong");
			}
			
			
		}catch(Exception e) {
			return new ResponceDTO("Failed!!",e.getMessage());
		}
	}
	
	//Update Movie
	public ResponceDTO updateMovie(Movies data) {
		try {
			 moviesRepository.save(data);
			 return new ResponceDTO("Success!!","Theater Updated Successfully");
		}catch(Exception e) {
			return new ResponceDTO("Failed!!",e.getMessage());
		}
	}
	
	public ApplicationUser getUser(String email) {
		return userRepository.findByEmail(email).get();
	}
	
}
