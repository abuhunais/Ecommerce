package com.edison.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edison.model.Customer;


@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findByEmailAndPhoneNumber(String email, String phoneNumber);

}
