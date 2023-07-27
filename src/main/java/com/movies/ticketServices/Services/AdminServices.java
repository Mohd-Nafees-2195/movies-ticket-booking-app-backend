package com.movies.ticketServices.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movies.ticketServices.Model.ApplicationAdmin;
import com.movies.ticketServices.Model.Movies;
import com.movies.ticketServices.Model.Theatres;
import com.movies.ticketServices.Model.DTO.MoviesDTO;
import com.movies.ticketServices.Model.DTO.ResponceDTO;
import com.movies.ticketServices.Model.DTO.TheatreDTO;
import com.movies.ticketServices.Repository.AdminRepository;
import com.movies.ticketServices.Repository.MoviesRepository;
import com.movies.ticketServices.Repository.TheatresRepository;

@Service
public class AdminServices {
	
	@Autowired
	private AdminRepository adminRepository;
	
	@Autowired
	private MoviesRepository moviesRepository;

	
	@Autowired
	private TheatresRepository theatresRepository;

	
	//Adding new Theater
	public ResponceDTO insertNewTheatre(TheatreDTO theater) {
		try {
			ApplicationAdmin admin=adminRepository.findByEmail(theater.getEmail()).get();
			Theatres theater1=theatresRepository.findById(admin.getUserId()).get();
			if(theater1==null) {
				Theatres newTheatre=new Theatres(0,theater.getTheatreName(),theater.getTheatreCity(),theater.getTotalSeats(),admin.getUserId());
				theatresRepository.save(newTheatre);
				return new ResponceDTO("Failed!!","Theatre Added Successfully");
			}else {
				return new ResponceDTO("Failed!!","Theater Already Exits");
			}
			
		}catch(Exception e) {
			return new ResponceDTO("Failed!!",e.getMessage());
		}
	}
	
	//Insert new Movie
		public ResponceDTO insertNewMovie(MoviesDTO newMovie,byte[] imageData) {
			try {
				//newMovie.setImageData(file);
				
				System.out.println(newMovie.getMovieName()+" /// "+newMovie.getMovieTitle()+" ");
				ApplicationAdmin admin=adminRepository.findByEmail(newMovie.getEmail()).get();
				if(admin==null)
					return new ResponceDTO("Failed!!","wrong email");
				Theatres theater=theatresRepository.findById(admin.getUserId()).get();
				if(theater!=null) {
					Movies movie=new Movies(0,newMovie.getMovieName(),newMovie.getMovieTitle(),theater.getTheatreId(),imageData);
					moviesRepository.save(movie);
					return new ResponceDTO("Success!!","New Movie Added Successfully");
				}else {
					return new ResponceDTO("Failed!!","Failed to add movie");
				}
				
			}catch(Exception e) {
				return new ResponceDTO("Failed!!","Something Went Wrong");
			}
		}
	
}
