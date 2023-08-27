package com.jsp.medico.exception;

import lombok.Getter;

@Getter
public class AddressIdNotFoundException extends RuntimeException {
	
	private String message;

	public AddressIdNotFoundException(String message) {
		super();
		this.message = message;
	}
	

}
