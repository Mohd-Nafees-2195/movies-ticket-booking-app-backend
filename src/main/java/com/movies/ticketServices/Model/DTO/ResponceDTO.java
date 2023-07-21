package com.movies.ticketServices.Model.DTO;

public class ResponceDTO {
	
	private String message;
	private String reason;
	
	
	
	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public String getReason() {
		return reason;
	}



	public void setReason(String reason) {
		this.reason = reason;
	}



	public ResponceDTO() {
		super();
	}



	public ResponceDTO(String message, String reason) {
		super();
		this.message = message;
		this.reason = reason;
	}
	
	

}
