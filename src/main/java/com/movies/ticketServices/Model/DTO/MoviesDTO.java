package com.movies.ticketServices.Model.DTO;

public class MoviesDTO {

	 private Integer theaterId;
	 private String movieName;
	 private String movieTitle;
	 private double price;
	 private String email;
	 
	 
	public MoviesDTO() {
		super();
	}
	
	public MoviesDTO(Integer theaterId,String movieName, String movieTitle,double price,String email) {
		super();
		this.theaterId=theaterId;
		this.movieName = movieName;
		this.movieTitle = movieTitle;
		this.price=price;
		this.email=email;
	}
	
	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getTheaterId() {
		return theaterId;
	}


	public void setTheaterId(Integer theaterId) {
		this.theaterId = theaterId;
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
