package com.edison.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import com.edison.model.AuthenticateRequest;
import com.edison.model.AuthenticationResponse;
import com.edison.model.User;
import com.edison.model.ValidateResponse;

@FeignClient(name="backendFeign",url="localhost:8080/auth")
public interface BackendFeign {
	@PostMapping("/validate")
	public AuthenticationResponse validateJwt(@RequestHeader("Authorization") String jwtRequestHeader);
	@PostMapping("/authenticate")
	public ValidateResponse generateJwt(@RequestBody AuthenticateRequest request);
	@PostMapping("/register")
	public String registerUser(@RequestBody User user);
}
