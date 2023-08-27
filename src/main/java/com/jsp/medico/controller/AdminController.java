package com.jsp.medico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.medico.dto.AdminDto;
import com.jsp.medico.entity.Admin;
import com.jsp.medico.service.AdminService;
import com.jsp.medico.util.ResponseStructure;

@Repository
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	private AdminService service;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(@RequestBody Admin admin){
		return service.saveAdmin(admin);
	}

	@GetMapping
	public ResponseEntity<ResponseStructure<AdminDto>> findAdminById(@RequestParam int adminId){
		return service.findAdminById(adminId);
	}
	
	@PutMapping
	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin
	(@RequestParam int adminId ,@RequestBody Admin admin){
		return service.updateAdmin(adminId,admin);
	}
	
	@DeleteMapping
	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(@RequestParam int adminId){
		return service.deleteAdmin(adminId);
	}
}
