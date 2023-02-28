package com.authentication.edison.service;

import com.authentication.edison.model.PersonalDetails;
import com.authentication.edison.model.UserRegistration;

public interface UserDetailsService {

	public void savePersonalDetails(UserRegistration userRegistration);
	
	public PersonalDetails getPersonalDetials(String jwt);
}
