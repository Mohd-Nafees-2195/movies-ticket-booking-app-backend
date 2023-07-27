package com.movies.ticketServices.Model.DTO;


public class RegistrationDTO {

	private String username;
	private String email;
	private String password;


	public RegistrationDTO() {
		super();
	}


//	public RegistrationDTO(String username, String password) {
//		super();
//		this.username = username;
//		this.password = password;
//	}


	public RegistrationDTO(String username, String email, String password) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
	}




	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public String toString() {
		return "RegistrationDTO [username=" + username + ", password=" + password + "]";
	}


}
