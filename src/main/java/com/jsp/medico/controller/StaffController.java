package com.jsp.medico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.medico.dto.StaffDto;
import com.jsp.medico.entity.Staff;
import com.jsp.medico.service.StaffService;
import com.jsp.medico.util.ResponseStructure;

import lombok.Getter;

@RestController
@RequestMapping("/staff")
public class StaffController {
	
	@Autowired
	private StaffService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<StaffDto>> saveStaff
	(@RequestParam int adminId, @RequestParam int storeId , @RequestBody Staff staff){
		return service.saveStaff(adminId , storeId , staff);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<StaffDto>> updateStaff
	(@RequestParam int staffId , @RequestBody Staff staff){
		return service.updateStaff(staffId , staff);
	}
	
	@GetMapping
	private ResponseEntity<ResponseStructure<StaffDto>> findStaffById 
	(@RequestParam int staffId){
		return service.findStaffById(staffId);
	}
	
	@DeleteMapping
	private ResponseEntity<ResponseStructure<StaffDto>> deleteStaff
	(@RequestParam int staffId){
		return service.deleteStaff(staffId);
	}
	

	

}
