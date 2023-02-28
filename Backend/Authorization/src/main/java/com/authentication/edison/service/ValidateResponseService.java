package com.authentication.edison.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.authentication.edison.model.UserDetail;
import com.authentication.edison.model.ValidateResponse;

@Service
public class ValidateResponseService {
	
	@Autowired
	private ValidateResponse validateResponse;
	
	public ValidateResponse getValidateResponse(String jwt,UserDetail userDetail) {
		validateResponse.setJwt(jwt);
		return validateResponse;
	}

}
