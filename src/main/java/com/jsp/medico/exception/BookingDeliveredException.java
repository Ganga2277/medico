package com.jsp.medico.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookingDeliveredException extends RuntimeException {

	private String message;
}
