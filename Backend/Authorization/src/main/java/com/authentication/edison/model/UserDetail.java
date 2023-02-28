package com.authentication.edison.model;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;
import lombok.ToString;
//inbuilt to validate
@ToString
@Component

@NoArgsConstructor

public class UserDetail implements UserDetails{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
//	@NotNull
	private String emailId;//big
//	@NotNull
	private String userName;
	private String address;
	private Long number;
//	@NotNull
	private String password;
	
	public UserDetail(PersonalDetails personalDetails) {
		this.emailId = personalDetails.getEmailId();
		this.userName = personalDetails.getUserName();
		this.password = personalDetails.getPassword();
		this.address = personalDetails.getAddress();
		this.number= personalDetails.getNumber();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return new ArrayList<>();
	}

	@Override
	public String getPassword() {
		return this.password;
	}

	@Override
	public String getUsername() {
		return this.userName;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}




//	public String getTypeOfUser() {
//		return typeOfUser;
//	}
//
//
//
//	public void setTypeOfUser(String typeOfUser) {
//		this.typeOfUser = typeOfUser;
//	}
//
//	public String getTrainerId() {
//		return trainerId;
//	}
//
//	public void setTrainerId(String trainerId) {
//		this.trainerId = trainerId;
//	}
//
//	public String getCoachId() {
//		return coachId;
//	}
//
//	public void setCoachId(String coachId) {
//		this.coachId = coachId;
//	}	

}
