package com.movies.ticketServices.Services;

import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movies.ticketServices.Model.ApplicationUser;
import com.movies.ticketServices.Model.Role;
import com.movies.ticketServices.Model.DTO.LoginDTO;
import com.movies.ticketServices.Model.DTO.LoginResponseDTO;
import com.movies.ticketServices.Model.DTO.RegistrationResponseDTO;
import com.movies.ticketServices.Repository.RoleRepository;
import com.movies.ticketServices.Repository.UserRepository;

@Service
@Transactional
public class AuthenticationService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;


	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenService tokenService;


	//Registration Service
//	public ApplicationUser registerUser(String username,String password) {
//
//		String encodedPassword=passwordEncoder.encode(password);
//		Role userRole= roleRepository.findByAuthority("USER").get();
//
//		Set<Role> authorities=new HashSet<>();
//		authorities.add(userRole);
//
//			ApplicationUser user=userRepository.findByUsername(username).get();
//				if(user.getUsername().matches(username)) {
//					return new ApplicationUser(0,"user already Exit","No Password",null);
//				}else {
//					return userRepository.save(new ApplicationUser(0,username,encodedPassword,authorities));
//				}
////			System.out.println("PSQL Exception");
//
//		//return userRepository.save(new ApplicationUser(0,username,encodedPassword,authorities));
//	}
 public RegistrationResponseDTO registerUser(String username,String email,String password) {

		String encodedPassword=passwordEncoder.encode(password);
		Role userRole= roleRepository.findByAuthority("USER").get();

		Set<Role> authorities=new HashSet<>();
		authorities.add(userRole);

//			Optional<ApplicationUser> getUserDetails=userRepository.findByUsername(email);
		
		Optional<ApplicationUser> getUserDetails=userRepository.findByEmail(email);
			if(getUserDetails.isEmpty()) {
				//System.out.println("Not Empty");
				//String emailVerificationToken=UUID.randomUUID().toString();
				
				userRepository.save(new ApplicationUser(0,username,email,encodedPassword,false,authorities));
				 return new RegistrationResponseDTO("Registration Success","NA");
			}else {
				return new RegistrationResponseDTO("Registration Failed","user Already Exit");
				//System.out.println("******** (((())))))");
//				ApplicationUser user=getUserDetails.get();
//				if(user.getEmail().matches(email)) {
					
//				}else {
//					 //String emailVerificationToken=UUID.randomUUID().toString();
//					 userRepository.save(new ApplicationUser(0,username,email,encodedPassword,authorities));
//					 return new RegistrationResponseDTO("Registration Success","");
//				}
			}
	}
 
 //Email Verification
//    public LoginResponseDTO emailVerification() {
//    	
//    }

	//Login Service
 
	public LoginResponseDTO loginUser(LoginDTO body) {
		try {

			//String encodedPassword=passwordEncoder.encode(password);
			System.out.println("Before Authentication");
			
			ApplicationUser user= userRepository.findByEmail(body.getEmail()).get(); 
			//System.out.println("********"+user.getEmail()+" (((())))))"+user.getUsername());
//			if(user.getEmail()==null||user==null) {
//				return new LoginResponseDTO(new ApplicationUser(0,"Login Failed","Usr Not Found!! Please Register First","",null),"");
//			}
			
			Authentication auth=authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(user.getUsername(), body.getPassword())
					);

			
			String token=tokenService.generateJwt(auth);
			//String username[]=email.split("@");
			
			return new LoginResponseDTO(userRepository.findByEmail(body.getEmail()).get(),token);

		}catch(NoSuchElementException e) {
			return new LoginResponseDTO(new ApplicationUser(0,"Login Failed","Usr Not Found!! Please Register First","",false,null),"");
		}catch(AuthenticationException e) {
			return new LoginResponseDTO(new ApplicationUser(0,"Login Failed","Incorrect Email or Password","",false,null),"");
		} 
	}

}
