package com.jsp.medico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.medico.dto.BookingDto;
import com.jsp.medico.entity.Booking;
import com.jsp.medico.service.BookingService;
import com.jsp.medico.util.ResponseStructure;

@RestController
@RequestMapping("/booking")
public class BookingController {

	
	@Autowired
	private BookingService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Booking>> saveBooking
	(@RequestParam int customerId , @RequestParam int medicineId , @RequestBody BookingDto bookingDto){
		return service.saveBooking(customerId,medicineId,bookingDto);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<Booking>>  cancelBooking(@RequestParam int bookingId){
		return service.cancelBooking(bookingId);
	}
}
