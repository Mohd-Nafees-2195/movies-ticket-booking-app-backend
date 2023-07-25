package com.movies.ticketServices.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.movies.ticketServices.Model.Movies;
import com.movies.ticketServices.Model.DTO.ResponceDTO;
import com.movies.ticketServices.Repository.MoviesRepository;

@Service
public class MovieServices {

	@Autowired
	private MoviesRepository moviesRepository;
	
	//Get All Movies 
	public Iterable<Movies> getAllMovies(){
		return moviesRepository.findAll();
	}
	
	//Insert new Movie
	public ResponceDTO insertNewMovie(Movies newMovie) {
		try {
			moviesRepository.save(newMovie);
			return new ResponceDTO("Success!!","New Movie Added Successfully");
		}catch(Exception e) {
			return new ResponceDTO("Failed!!","Something Went Wrong");
		}
	}
}
