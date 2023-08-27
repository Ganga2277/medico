package com.jsp.medico.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddressAlreadyMappedToOtherCustomer extends RuntimeException {

	private String message;

}
