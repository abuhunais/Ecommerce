package com.authentication.edison.model;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

import lombok.Data;
//fetching regsterd details
@Component
@Data
public class UserRegistration {
//	@NotNull
	private String emailId;///big
//	@NotNull 
	private String userName;
//	@NotNull
	private String password;
	private String address;
	private Long number;
	
	//private String typeOfUser;
}
