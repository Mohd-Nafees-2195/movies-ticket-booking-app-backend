package com.movies.ticketServices.Model;

import java.time.LocalDate;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="ticket_tbl")
public class Tickets {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO )
	@Column(name="ticket_id")
	private Integer ticketId;
	
	@Column(name="date")
	private LocalDate date;
	
	@Column(name="start_time")
	private LocalTime startTime;
	
	@Column(name="end_time")
	private LocalTime endTime;
	
	@Column(name="seat_number")
	private Integer seatNumber;
	
	@Column(name="movie_id")
	private Integer movieId;
	
	@Column(name="user_id")
	private Integer userId;

	public Tickets() {
		super();
	}

	public Tickets(Integer ticketId, LocalDate date, LocalTime startTime, LocalTime endTime, Integer seatNumber,
			Integer movieId, Integer userId) {
		super();
		this.ticketId = ticketId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.seatNumber = seatNumber;
		this.movieId = movieId;
		this.userId = userId;
	}

	public Integer getTicketId() {
		return ticketId;
	}

	public void setTicketId(Integer ticketId) {
		this.ticketId = ticketId;
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

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	
	
}
