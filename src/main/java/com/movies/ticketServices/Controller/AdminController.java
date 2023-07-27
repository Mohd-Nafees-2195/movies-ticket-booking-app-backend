package com.movies.ticketServices.Controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.movies.ticketServices.Model.Movies;
import com.movies.ticketServices.Model.Theatres;
import com.movies.ticketServices.Model.DTO.MoviesDTO;
import com.movies.ticketServices.Model.DTO.ResponceDTO;
import com.movies.ticketServices.Model.DTO.TheatreDTO;
import com.movies.ticketServices.Services.AdminServices;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

//	@Autowired
//	private MovieServices movieServices;
	
	@Autowired
	private AdminServices adminServices;

	@GetMapping("/")
	public String adminAccess() {
		return "Hello Admin";
	}



	//Adding new Movie
	@PostMapping("/insert-movie")
	@ResponseBody
	public ResponceDTO insertNewMovie(MoviesDTO newMovie,@RequestParam("file") MultipartFile file) {
		System.out.println(newMovie.getMovieName()+" /// "+newMovie.getMovieTitle());
		if(newMovie.getMovieName()!=null&&newMovie.getMovieTitle()!=null&&newMovie.getEmail()!=null) {
			try {
				byte[] imageData=file.getBytes();
				return adminServices.insertNewMovie(newMovie,imageData);
			}catch(IOException e) {
				return new ResponceDTO("Failed!!",e.getMessage());
			}
			//return new ResponceDTO("Failed!!","Something Went Wrong");
		}
		else {
			return new ResponceDTO("Failed!!","Something Went Wrong");
		}
	}
	
	//Adding new Theater
	@PostMapping("/insert-theater")
	public ResponceDTO insertNewTheatre(@RequestBody TheatreDTO theater) {
		if(theater.getEmail()==null||theater.getTheatreCity()==null||theater.getTheatreName()==null||theater.getTotalSeats()<=0) {
			return new ResponceDTO("Failed!!","Some fields are missing");
		}else {
			adminServices.insertNewTheatre(theater);
		}
		return new ResponceDTO("","");
	}
	
//	@PostMapping("/insert/{movieName}/{movieTitle}")
//	public ResponceDTO insertNewMovie(@PathVariable("movieName") String movieName ,@PathVariable("movieTitle") String movieTitle,@RequestParam("file") MultipartFile file) {
//		
//		System.out.println(movieName+" /// "+movieTitle+" ");
//		if(movieName!=null&&movieTitle!=null) {
//			try {
//				if(file!=null){
//					Movies newMovie=new Movies(0,movieName,movieTitle,file.getBytes());
//					return movieServices.insertNewMovie(newMovie);
//				}else {
//					return new ResponceDTO("Failed!!","Please select a file");
//				}
//				
//			}catch(IOException e) {
//				return new ResponceDTO("Failed!!",e.getMessage());
//			}
//			//return new ResponceDTO("Failed!!","Something Went Wrong");
//		}
//		else {
//			return new ResponceDTO("Failed!!","Something Went Wrong");
//		}
//	}
}
