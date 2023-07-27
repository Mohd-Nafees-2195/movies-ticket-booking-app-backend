package com.movies.ticketServices.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="theatres_tbl")
public class Theatres {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="theatre_id")
	private Integer theatreId;
	
	@Column(name="theatre_name")
	private String theatrename;
	
	@Column(name="theatre_city")
	private String theatreCity;
	
	@Column(name="total_seats")
	private Integer totalSeats;
	
	@Column(name="admin_id")
	private Integer adminId;

	public Theatres() {
		super();
	}

	public Theatres(Integer theatreId, String theatrename, String theatreCity, Integer totalSeats, Integer adminId) {
		super();
		this.theatreId = theatreId;
		this.theatrename = theatrename;
		this.theatreCity = theatreCity;
		this.totalSeats = totalSeats;
		this.adminId = adminId;
	}

	public Integer getTheatreId() {
		return theatreId;
	}

	public void setTheatreId(Integer theatreId) {
		this.theatreId = theatreId;
	}

	public String getTheatrename() {
		return theatrename;
	}

	public void setTheatrename(String theatrename) {
		this.theatrename = theatrename;
	}

	public String getTheatreCity() {
		return theatreCity;
	}

	public void setTheatreCity(String theatreCity) {
		this.theatreCity = theatreCity;
	}

	public Integer getTotalSeats() {
		return totalSeats;
	}

	public void setTotalSeats(Integer totalSeats) {
		this.totalSeats = totalSeats;
	}

	public Integer getAdminId() {
		return adminId;
	}

	public void setAdminId(Integer adminId) {
		this.adminId = adminId;
	}
	
	
	
}
