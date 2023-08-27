package com.jsp.medico.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jsp.medico.entity.Customer;

public interface CustomerRepo extends JpaRepository<Customer, Integer>{

	@Query("select c from Customer c where c.email=?1")
	Optional<Customer> findByEmail(String email);

	 
}
