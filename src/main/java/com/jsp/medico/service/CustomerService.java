package com.jsp.medico.service;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.medico.dao.AddressDao;
import com.jsp.medico.dao.CustomerDao;
import com.jsp.medico.dto.AddressDto;
import com.jsp.medico.dto.BookingDto;
import com.jsp.medico.dto.CustomerDto;
import com.jsp.medico.entity.Address;
import com.jsp.medico.entity.Booking;
import com.jsp.medico.entity.Customer;
import com.jsp.medico.exception.AddressAlreadyMappedToOtherCustomer;
import com.jsp.medico.exception.AddressIdNotFoundException;
import com.jsp.medico.exception.AddressMappedToMedicalStore;
import com.jsp.medico.exception.CustomerIdNotFoundException;
import com.jsp.medico.exception.EmailNotFoundException;
import com.jsp.medico.exception.InvalidPasswordException;
import com.jsp.medico.exception.PhoneNumberNotValidException;
import com.jsp.medico.util.ResponseStructure;

@Service
public class CustomerService {

	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private AddressDao addressDao;
	
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<CustomerDto>> saveCustomer(int addressId, Customer customer) {
		Address dbAddress=addressDao.findAddressById(addressId);
//		when addressId =100 your db address==null
//		dbAddress==is having id name also relationship entity that is your customer id=5
		
	
//		checking addressId is present or not
		if(dbAddress!=null) {
			if(dbAddress.getMedicalStore()!=null) {
				throw new AddressMappedToMedicalStore("sorry this address is mapped to medicalstore");
			}
			
			if(dbAddress.getCustomer()!=null) {
				throw new AddressAlreadyMappedToOtherCustomer("sorry");
			}
			dbAddress.setCustomer(customer);
//			yes addressId is present
			List<Address> addresses=new ArrayList<Address>();
			addresses.add(dbAddress);
//			customer=only the own attributes not a relationship attributes
			customer.setAddresses(addresses);
//			now customer is having adddress also
			Customer dbCustomer=customerDao.saveCustomer(customer);
//	dbCustomer is having its own attributes then relationship attributes that is List address and List of bookings but its null
//			but list  of address is present
//			Copy Customer to customerDto
//			but this customer dto is having ListofaddresssesDto and ListOfBooking dto but still it is null;
//			copy List of Addressto addressDto
			CustomerDto customerDto=this.modelMapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto> addressDtos;
		for(Address address:dbCustomer.getAddresses()) {
			AddressDto addressDto=this.modelMapper.map(address, AddressDto.class);
			addressDtos=new ArrayList<AddressDto>();
			addressDtos.add(addressDto);
			customerDto.setAddressess(addressDtos);
		} 
			customerDto.setBookingsDtos(null);
			ResponseStructure<CustomerDto> structure=new ResponseStructure<CustomerDto>();
			structure.setMessage("CustometrAddedsuccessfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(customerDto);
			return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.CREATED);
			
		}else {
			throw new AddressIdNotFoundException("Sorry failed to add customer");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> updateCustomer(int customerId, Customer customer) {
		Customer dbCustomer = customerDao.updateCustomer(customerId,customer);
		if(dbCustomer!=null) {
			CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto> addressDtos;
			for(Address address:dbCustomer.getAddresses()) {
				AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
				addressDtos=new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddressess(addressDtos);
			}
			    customerDto.setBookingsDtos(null);
	
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
		    structure.setMessage("Customer Updated Successfully");
		    structure.setStatus(HttpStatus.OK.value());
		    structure.setData(customerDto);
		    return new ResponseEntity<ResponseStructure<CustomerDto>> (structure , HttpStatus.OK);

		}else {
			throw new CustomerIdNotFoundException("Sorry Failed Upadte Customer");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> findCustomerById(int customerId) {
		Customer dbCustomer = customerDao.findCustomerById(customerId);
		if(dbCustomer!=null) {
			CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto> addressDtos;
			for(Address address:dbCustomer.getAddresses()) {
				AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
				addressDtos=new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddressess(addressDtos);
			}
			    customerDto.setBookingsDtos(null);
	
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
		    structure.setMessage("Customer Fetched Successfully");
		    structure.setStatus(HttpStatus.FOUND.value());
		    structure.setData(customerDto);
		    return new ResponseEntity<ResponseStructure<CustomerDto>> (structure , HttpStatus.FOUND);

		}else {
			throw new CustomerIdNotFoundException("Sorry Failed Fetch Customer");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> deleteCustomer(int customerId) {
		Customer dbCustomer = customerDao.deleteCustomer(customerId);
		if(dbCustomer!=null) {
			CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);
			List<AddressDto> addressDtos;
			for(Address address:dbCustomer.getAddresses()) {
				AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
				addressDtos=new ArrayList<AddressDto>();
				addressDtos.add(addressDto);
				customerDto.setAddressess(addressDtos);
			}
			    customerDto.setBookingsDtos(null);
	
			ResponseStructure<CustomerDto> structure = new ResponseStructure<CustomerDto>();
		    structure.setMessage("Customer Deleted Successfully");
		    structure.setStatus(HttpStatus.GONE.value());
		    structure.setData(customerDto);
		    return new ResponseEntity<ResponseStructure<CustomerDto>> (structure , HttpStatus.GONE);

		}else {
			throw new CustomerIdNotFoundException("Sorry Failed Delete Customer");
		}
		
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> loginCustomer(String email, String password) {
		Customer dbCustomer = customerDao.findByEmail(email);
		if(dbCustomer!=null) {
			if(dbCustomer.getPassword().equals(password)) {
//				login success
				CustomerDto customerDto = this.modelMapper.map(dbCustomer, CustomerDto.class);
				List<AddressDto> addressDtos;
				for(Address address:dbCustomer.getAddresses()) {
					AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
					addressDtos=new ArrayList<AddressDto>();
					addressDtos.add(addressDto);
					customerDto.setAddressess(addressDtos);
				}
				List<BookingDto> bookingDtos;
				for(Booking booking:dbCustomer.getBookings()) {
					BookingDto bookingDto = this.modelMapper.map(booking, BookingDto.class);
					bookingDtos = new ArrayList<BookingDto>();
					bookingDtos.add(bookingDto);
					customerDto.setBookingsDtos(bookingDtos);
				}
				
				ResponseStructure<CustomerDto> structure=new ResponseStructure<CustomerDto>();
				structure.setMessage("Customer Login Success WELCOME TO ONLINEPHARMACY");
				structure.setStatus(HttpStatus.OK.value());
				structure.setData(customerDto);
				return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.OK);
				
			}else {
				throw new InvalidPasswordException("Sorry failed to Login");
			}
		}else {
			throw new EmailNotFoundException("Sorry failed to Login");
		}
	}

	public ResponseEntity<ResponseStructure<CustomerDto>> forgotPassword(String email, long phone, String password) {
		 Customer customer =customerDao.findByEmail(email);
		 if(customer!=null) {
//			 customer is present
			 if(customer.getPhoneNumber()==phone) {
//				 phone is registered
				 customer.setPassword(password);
				 customerDao.updateCustomer(customer.getCustomerId(), customer);
				 
				 CustomerDto customerDto = this.modelMapper.map(customer, CustomerDto.class);
					List<AddressDto> addressDtos;
					for(Address address:customer.getAddresses()) {
						AddressDto addressDto = this.modelMapper.map(address, AddressDto.class);
						addressDtos=new ArrayList<AddressDto>();
						addressDtos.add(addressDto);
						customerDto.setAddressess(addressDtos);
					}
					List<BookingDto> bookingDtos;
					for(Booking booking:customer.getBookings()) {
						BookingDto bookingDto = this.modelMapper.map(booking, BookingDto.class);
						bookingDtos = new ArrayList<BookingDto>();
						bookingDtos.add(bookingDto);
						customerDto.setBookingsDtos(bookingDtos);
					}
					
					ResponseStructure<CustomerDto> structure=new ResponseStructure<CustomerDto>();
					structure.setMessage("Customer Password reset successfully");
					structure.setStatus(HttpStatus.OK.value());
					structure.setData(customerDto);
					return new ResponseEntity<ResponseStructure<CustomerDto>>(structure,HttpStatus.OK);
					
			 }else {
				throw new PhoneNumberNotValidException("Sorry failed to reset password Please enter Registered PhoneNumber");
			}
			 
		 }else {
			 throw new EmailNotFoundException("sorry failed to reset password please enter valid email");
		 }
	}

	
	
}
