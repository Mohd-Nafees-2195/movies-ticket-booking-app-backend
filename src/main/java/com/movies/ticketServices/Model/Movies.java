package com.movies.ticketServices.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

@Entity
@Table(name="movies_tbl")
public class Movies {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="movie_id")
	private Integer movieId;
	
	@Column(name="movie_name")
	private String movieName;

	@Column(name="movie_title")
	private String movieTitle;

	@Column(name="theatre_id")
	private Integer theatetId;
	
	@Lob
	private byte[] imageData;
	



	public Movies() {
		super();
	}



	public Movies(Integer movieId, String movieName, String movieTitle,Integer theatetId ,byte[] imageData) {
		super();
		this.movieId = movieId;
		this.movieName = movieName;
		this.movieTitle = movieTitle;
		this.theatetId=theatetId;
		this.imageData = imageData;
	}



	public Integer getTheatetId() {
		return theatetId;
	}



	public void setTheatetId(Integer theatetId) {
		this.theatetId = theatetId;
	}



	public Integer getMovieId() {
		return movieId;
	}



	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
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



	public byte[] getImageData() {
		return imageData;
	}



	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}



}
