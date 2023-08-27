package com.jsp.medico.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AddressDto {
      
	private int addressId;
	private String streetName;
	private String city;
	private String state;
	private long pincode;
}
