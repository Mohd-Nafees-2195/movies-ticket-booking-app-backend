package com.movies.ticketServices.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.movies.ticketServices.Model.ApplicationUser;
import com.movies.ticketServices.Model.DTO.LoginDTO;
import com.movies.ticketServices.Model.DTO.LoginResponseDTO;
import com.movies.ticketServices.Model.DTO.RegistrationDTO;
import com.movies.ticketServices.Model.DTO.RegistrationResponseDTO;
import com.movies.ticketServices.Services.AuthenticationService;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

	@Autowired
	private AuthenticationService authenticationService;

//	@PostMapping("/register")
//	public ApplicationUser registerUser(@RequestBody RegistrationDTO body) {
//		return authenticationService.registerUser(body.getUsername(),body.getPassword());
//	}

	@PostMapping("/register")
	public RegistrationResponseDTO registerUser(@RequestBody RegistrationDTO body) {
		if(body.getUsername()==null||body.getEmail()==null||body.getPassword()==null) {
			return new RegistrationResponseDTO("Registration Failed","Please Enter all fields");
		}
		return authenticationService.registerUser(body.getUsername(),body.getEmail(),body.getPassword());
	}
	
	@GetMapping("/verifyEmail")
	public String verifyEmail(@RequestParam("token") String token) {
		
		return "";
	}

	@PostMapping("/login")
	public LoginResponseDTO loginUser(@RequestBody LoginDTO body) {
		//System.out.println(body.getUsername()+" "+body.getPassword());//"Registration Failed","Please Enter all fields"
		
		if(body.getEmail()==null||body.getPassword()==null) {
			return new LoginResponseDTO(new ApplicationUser(0,"Login Failed","Please Fill All Details","",false,null),"");
		}
		return authenticationService.loginUser(body);
	}
}
