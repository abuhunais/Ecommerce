package com.edison.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.edison.model.Customer;
import com.edison.model.Refund;
import com.edison.model.RefundStatus;
import com.edison.model.ReturnRequest;
import com.edison.model.ReturnRequestStatus;
import com.edison.repo.CustomerRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerService{


	@Autowired
	private CustomerRepository customerRepository;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	 public Customer registerUser(Customer customer) {
	        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
	        return customerRepository.save(customer);
	    }
	
	public String login(Customer customer) {
		Customer existingCustomer = customerRepository.findByEmailAndPhoneNumber(customer.getEmail(), customer.getPhoneNumber());
		if (existingCustomer != null) {
		return "LoggedIn";
		}
		return null;
		}

	
	public Customer updateCustomerAccount(Long customerId, Customer customer) {
		Customer customerToUpdate = customerRepository.findById(customerId).orElseThrow();
		customerToUpdate.setFirstName(customer.getFirstName());
		customerToUpdate.setLastName(customer.getLastName());
		customerToUpdate.setEmail(customer.getEmail());
		customerToUpdate.setPhoneNumber(customer.getPhoneNumber());
		customerToUpdate.setAddress(customer.getAddress());
		return customerRepository.save(customerToUpdate);
	}

	public ReturnRequest requestReturn(Long customerId, ReturnRequest returnRequest) {
		Customer customer = customerRepository.findById(customerId).orElseThrow();
		returnRequest.setCustomer(customer);
		returnRequest.setStatus(ReturnRequestStatus.REQUESTED);
		return returnRequest;
	}

	
	public Refund processRefund(Long customerId, Refund refund) {
		Customer customer = customerRepository.findById(customerId).orElseThrow();
		refund.setCustomer(customer);
		refund.setStatus(RefundStatus.PROCESSED);
		return refund;
	}


}