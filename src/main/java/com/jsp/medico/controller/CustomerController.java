package com.jsp.medico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.medico.dto.CustomerDto;
import com.jsp.medico.entity.Customer;
import com.jsp.medico.service.CustomerService;
import com.jsp.medico.util.ResponseStructure;

@RestController
@RequestMapping("/customer")
public class CustomerController {

	@Autowired
	private CustomerService service;
	
	@PostMapping
	private ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer
	(@RequestParam int addressId,@RequestBody Customer customer){
		return service.saveCustomer(addressId,customer);
	}
	
	@PutMapping
	private ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(@RequestParam int customerId,@RequestBody Customer customer){
		return service.updateCustomer(customerId,customer);
	}
	
	@GetMapping
	private ResponseEntity<ResponseStructure<CustomerDto>> findCustomerById(@RequestParam int customerId){
		return service.findCustomerById(customerId);
	}
	
	@DeleteMapping
	private ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(@RequestParam int customerId){
		return service.deleteCustomer(customerId);
	}
	
	@GetMapping("/login")
	private ResponseEntity<ResponseStructure<CustomerDto>> loginCustomer(@RequestParam String email, @RequestParam String password){
		return service.loginCustomer(email,password);
	}
	
	@PutMapping("/forgotpassword")
	private ResponseEntity<ResponseStructure<CustomerDto>> forgotPassword(@RequestParam String email,@RequestParam long phone,@RequestParam String password){
		return service.forgotPassword(email,phone,password);
	}
}
