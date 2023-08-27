package com.jsp.medico.dao;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.medico.entity.Address;
import com.jsp.medico.entity.Customer;
import com.jsp.medico.repository.CustomerRepo;

import net.bytebuddy.asm.Advice.Return;

@Repository
public class CustomerDao {

	@Autowired
	private CustomerRepo repo;
	
	@Autowired
	private AddressDao addressDao;

	public Customer saveCustomer(Customer customer) {
		
		return repo.save(customer);
	}

	public Customer updateCustomer(int customerId, Customer customer) {
	Optional<Customer> optional	= repo.findById(customerId);
	if(optional.isPresent()) {
		customer.setCustomerId(customerId);
		customer.setAddresses(optional.get().getAddresses());
		customer.setBookings(optional.get().getBookings());
		 return repo.save(customer);
	}
		return null;
	}

	public Customer findCustomerById(int customerId) {
		Optional<Customer> optional = repo.findById(customerId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	public Customer deleteCustomer(int customerId) {
		Optional<Customer> optional = repo.findById(customerId);
		if(optional.isPresent()) {
			Customer customer = optional.get();
			repo.delete(customer);
			return customer;
		}
		return null;
	}

	public Customer findByEmail(String email) {
		Optional<Customer> optional = repo.findByEmail(email);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}

	
}
