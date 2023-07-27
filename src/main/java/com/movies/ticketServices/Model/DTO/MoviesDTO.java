package com.movies.ticketServices.Model.DTO;

public class MoviesDTO {

	 private String movieName;
	 private String movieTitle;
	 private String email;
	 
	 
	public MoviesDTO() {
		super();
	}
	
	
	
	
	
	public MoviesDTO(String movieName, String movieTitle,String email) {
		super();
		this.movieName = movieName;
		this.movieTitle = movieTitle;
		this.email=email;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMovieName() {
		return movieName;
	}
	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}
	public String getMovieTitle() {
		return movieTitle;
	}
	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}
	 
	 
}
