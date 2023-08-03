package com.movies.ticketServices.Model.DTO;

import java.time.LocalDate;
import java.time.LocalTime;

public class TicketDTO {
  
	private Integer ticketId;
	private String email;
	private LocalDate date;
	private LocalTime startTime;
	private LocalTime endTime;
	private Integer seatNumber;
	private Integer movieId;
	private Integer timeId;
	
	public TicketDTO() {
		super();
	}
	public TicketDTO(Integer ticketId,String email, LocalDate date, LocalTime startTime, LocalTime endTime, Integer seatNumber,
			Integer movieId,Integer timeId) {
		super();
		this.ticketId=ticketId;
		this.email = email;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.seatNumber = seatNumber;
		this.movieId = movieId;
		this.timeId=timeId;
	}
	
	
	public Integer getTicketId() {
		return ticketId;
	}
	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
	}
	public Integer getTimeId() {
		return timeId;
	}
	public void setTimeId(Integer timeId) {
		this.timeId = timeId;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getStartTime() {
		return startTime;
	}
	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}
	public LocalTime getEndTime() {
		return endTime;
	}
	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}
	public Integer getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	
	
}
