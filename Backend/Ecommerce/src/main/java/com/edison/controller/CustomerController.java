package com.edison.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.edison.model.Customer;
import com.edison.model.Refund;
import com.edison.model.ReturnRequest;
import com.edison.service.CustomerService;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
	        customer.setPassword(bCryptPasswordEncoder.encode(customer.getPassword()));
	        customerService.registerUser(customer);
	        return new ResponseEntity<String>("Registered",HttpStatus.ACCEPTED);
	    
	}

	
	@PostMapping("/customers/login")
	public String login(@RequestBody Customer customer) {
		return customerService.login(customer);
	}

	@PutMapping("/customers/{customerId}")
	public Customer updateCustomerAccount(@PathVariable("customerId") Long customerId, @RequestBody Customer customer) {
		return customerService.updateCustomerAccount(customerId, customer);
	}

	@PostMapping("/customers/{customerId}/returns")
	public ReturnRequest requestReturn(@PathVariable("customerId") Long customerId,
			@RequestBody ReturnRequest returnRequest) {
		return customerService.requestReturn(customerId, returnRequest);
	}

	@PutMapping("/customers/{customerId}/refunds")
	public Refund processRefund(@PathVariable("customerId") Long customerId, @RequestBody Refund refund) {
		return customerService.processRefund(customerId, refund);
	}
}
