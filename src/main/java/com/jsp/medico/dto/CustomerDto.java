package com.jsp.medico.dto;

import java.util.List;

import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CustomerDto {

	private int customerId;
	private String customerName;
	private String email;
	private long phoneNumber;
	
	@OneToMany
	private List<BookingDto> bookingsDtos;
	
	@OneToMany
	private List<AddressDto> addressess;
}
