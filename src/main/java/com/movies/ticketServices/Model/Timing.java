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
@Table(name="time_tbl")
public class Timing {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="time_id")
	private Integer timeId;
	
	@Column(name="date")
	private LocalDate date;
	
	@Column(name="start_time")
	private LocalTime startTime;
	
	@Column(name="end_time")
	private LocalTime endTime;
	
	@Column(name="available_seats")
	private Integer availableSeats;
	
	@Column(name="reserve_seats")
	private Integer reserveSeats;
	
	@Column(name="movie_id")
	private Integer movieId;

	public Timing() {
		super();
	}

	public Timing(Integer timeId, LocalDate date, LocalTime startTime, LocalTime endTime, Integer availableSeats,Integer reserveSeats,
			Integer movieId) {
		super();
		this.timeId = timeId;
		this.date = date;
		this.startTime = startTime;
		this.endTime = endTime;
		this.availableSeats = availableSeats;
		this.reserveSeats=reserveSeats;
		this.movieId = movieId;
	}

	
	public Integer getReserveSeats() {
		return reserveSeats;
	}

	public void setReserveSeats(Integer reserveSeats) {
		this.reserveSeats = reserveSeats;
	}

	public Integer getTimeId() {
		return timeId;
	}

	public void setTimeId(Integer timeId) {
		this.timeId = timeId;
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

	public Integer getAvailableSeats() {
		return availableSeats;
	}

	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}

	public Integer getMovieId() {
		return movieId;
	}

	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	
	
}
