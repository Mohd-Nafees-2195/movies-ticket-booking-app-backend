package com.movies.ticketServices.Model.DTO;

public class RegistrationResponseDTO {

	private String RegistrationMessage;
	private String RegistrationReasonIfFailed;




	public RegistrationResponseDTO() {
		super();
	}

	public RegistrationResponseDTO(String registrationMessage, String registrationReasonIfFailed) {
		super();
		RegistrationMessage = registrationMessage;
		RegistrationReasonIfFailed = registrationReasonIfFailed;
	}

	public String getRegistrationMessage() {
		return RegistrationMessage;
	}

	public void setRegistrationMessage(String registrationMessage) {
		RegistrationMessage = registrationMessage;
	}

	public String getRegistrationReasonIfFailed() {
		return RegistrationReasonIfFailed;
	}

	public void setRegistrationReasonIfFailed(String registrationReasonIfFailed) {
		RegistrationReasonIfFailed = registrationReasonIfFailed;
	}


}
