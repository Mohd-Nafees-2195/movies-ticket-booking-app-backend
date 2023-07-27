package com.movies.ticketServices.Model.DTO;

public class TheatreDTO {
   
	private String theatreName;
	private String theatreCity;
	private String email;
	private Integer totalSeats;
	
	
	public TheatreDTO() {
		super();
	}


	public TheatreDTO(String theatreName, String theatreCity, String email, Integer totalSeats) {
		super();
		this.theatreName = theatreName;
		this.theatreCity = theatreCity;
		this.email = email;
		this.totalSeats = totalSeats;
	}


	public String getTheatreName() {
		return theatreName;
	}


	public void setTheatreName(String theatreName) {
		this.theatreName = theatreName;
	}


	public String getTheatreCity() {
		return theatreCity;
	}


	public void setTheatreCity(String theatreCity) {
		this.theatreCity = theatreCity;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Integer getTotalSeats() {
		return totalSeats;
	}


	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}
	
	
	
	
}
