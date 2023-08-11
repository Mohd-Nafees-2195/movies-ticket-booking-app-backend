package com.movies.ticketServices.Controller;

import java.io.IOException;

import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.movies.ticketServices.Model.ApplicationUser;
import com.movies.ticketServices.Model.Movies;
import com.movies.ticketServices.Model.Theatres;
import com.movies.ticketServices.Model.DTO.MoviesDTO;
import com.movies.ticketServices.Model.DTO.ResponceDTO;
import com.movies.ticketServices.Model.DTO.TheatreDTO;
import com.movies.ticketServices.Services.AdminServices;
import com.movies.ticketServices.Services.UserService;

@RestController
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {

//	@Autowired
//	private MovieServices movieServices;
	
	@Autowired
	private AdminServices adminServices;
	
	@Autowired
	private UserService userService;

	@GetMapping("/")
	public String adminAccess() {
		return "Hello Admin";
	}



	//Adding new Movie
	@PostMapping("/insert-movie")
	@ResponseBody
	public ResponceDTO insertNewMovie(MoviesDTO newMovie,@RequestParam("file") MultipartFile file) {
		//System.out.println(newMovie.getMovieName()+" /// "+newMovie.getMovieTitle());
		if(newMovie.getMovieName()!=null&&newMovie.getMovieTitle()!=null&&newMovie.getEmail()!=null&&newMovie.getPrice()>0) {
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
	
	@PutMapping("/update-movie")
	@ResponseBody
	public ResponceDTO UpdateMovie(MoviesDTO newMovie,@RequestParam("id") Integer movieId,@RequestParam(value="file",required = false) MultipartFile file) {
		//System.out.println(newMovie.getMovieName()+" /// "+newMovie.getMovieTitle());
		if(newMovie.getMovieName()!=null&&newMovie.getMovieTitle()!=null&&newMovie.getEmail()!=null&&newMovie.getPrice()>0) {
			try {
				Movies data=userService.getMovie(movieId);
				if(data!=null) {
					data.setMovieName(newMovie.getMovieName());
					data.setMovieTitle(newMovie.getMovieTitle());
					data.setPrice(newMovie.getPrice());
					if(file!=null) {
						byte[] imageData=file.getBytes();
						data.setImageData(imageData);
					}
					return adminServices.updateMovie(data);
				}else {
					return null;
				}
			}catch(Exception e) {
				return new ResponceDTO("Failed!!",e.getMessage());
			}
		}
		else {
			return new ResponceDTO("Failed!!","Something Went Wrong");
		}
	}
	
	
	//Adding new Theater
	@PostMapping("/insert-theater")
	public ResponceDTO insertNewTheatre(@RequestBody TheatreDTO theater) {
		
		if(theater.getEmail()==null||theater.getTheatreCity()==null||theater.getTheatreName()==null||theater.getTotalSeats()<=0) {
			System.out.println("Empty = "+theater.getEmail()+" - "+theater.getTheatreCity()+" - "+theater.getTheatreName()+" - "+theater.getTotalSeats());
			return new ResponceDTO("Failed!!","Some fields are missing");
		}else {
			return adminServices.insertNewTheatre(theater);
		}
	}
	
	@GetMapping("/get-all-theater")
	public Iterable<Theatres> getAllTheater(@RequestParam("id") Integer adminId){
		return adminServices.getAllTheaters(adminId);
	}
	
	@GetMapping("/get-all-movies")
	public Iterable<Movies> getAllMovies(@RequestParam("id") Integer theaterId){
		return adminServices.getAllMovies(theaterId);
	}
	
	@DeleteMapping("/delete-movie")
	public ResponceDTO deleteMovie(@RequestParam("id") Integer movieId) {
		if(movieId!=null&& movieId>0) {
			return adminServices.deleteMovie(movieId);
		}else {
			return new ResponceDTO("Failed!!","Something Went Wrong");
		}
	}
	
	@DeleteMapping("/delete-theater")
	public ResponceDTO deleteTheater(@RequestParam("id") Integer theaterId) {
		if(theaterId<=0||theaterId==null)
			return new ResponceDTO("Failed!!","Something Went Wrong");
		return adminServices.deleteTheater(theaterId);
		
	}
	
	@GetMapping("/get-theater")
	public Theatres getTheater(@RequestParam("id") Integer theaterId) {
		if(theaterId<=0||theaterId==null)
			return null;
	    return adminServices.getTheater(theaterId);
	}
	
	@PutMapping("/update-theater")
	public ResponceDTO updateTheater(@RequestParam("id") Integer theaterId, @RequestBody TheatreDTO theater) {
		if(theater.getEmail()==null||theater.getTheatreCity()==null||theater.getTheatreName()==null||theater.getTotalSeats()<=0||theaterId<=0||theaterId==null) {
			System.out.println("Empty = "+theater.getEmail()+" - "+theater.getTheatreCity()+" - "+theater.getTheatreName()+" - "+theater.getTotalSeats());
			return new ResponceDTO("Failed!!","Some fields are missing");
		}else {
			return adminServices.updateTheater(theaterId,theater);
		}
		
	}
	
	@GetMapping("/get-admin")
	public ApplicationUser getAdmin(@RequestParam("email") String email) {
		return adminServices.getUser(email);
	}
}
