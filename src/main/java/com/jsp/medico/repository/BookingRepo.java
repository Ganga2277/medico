package com.jsp.medico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.medico.entity.Booking;

public interface BookingRepo extends JpaRepository<Booking, Integer>{

}
