package com.authentication.edison.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.authentication.edison.controller.FeignUserController;
import com.authentication.edison.model.PersonalDetails;
import com.authentication.edison.model.UserRegistration;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	Logger logger = LoggerFactory.getLogger("Auth-Controller-Logger");

	@Autowired
	private FeignUserController feigncontroller;
	
	@Override
	public void savePersonalDetails(UserRegistration userRegistration) {
		PersonalDetails personalDetails = new PersonalDetails();
		
		
		personalDetails.setEmailId(userRegistration.getEmailId());//big
		personalDetails.setNumber(userRegistration.getNumber());
		personalDetails.setAddress(userRegistration.getAddress());
		personalDetails.setUserName(userRegistration.getUserName());
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(userRegistration.getPassword());
		personalDetails.setPassword(encodedPassword);

		feigncontroller.saveUser(personalDetails);
		logger.info(personalDetails.getPassword());
		logger.info("User Data Inserted SuccessFUlly");
	}

	@Override
	public PersonalDetails getPersonalDetials(String jwt) {
		// TODO Auto-generated method stub
		return null;
	}

}
