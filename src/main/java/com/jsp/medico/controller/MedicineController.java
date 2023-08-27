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

import com.jsp.medico.entity.Medicine;
import com.jsp.medico.service.MedicineService;
import com.jsp.medico.util.ResponseStructure;

@RestController
@RequestMapping("/medicine")
public class MedicineController {

	
	@Autowired
	private MedicineService service;
	
	@PostMapping
	private ResponseEntity<ResponseStructure<Medicine>> saveMedicine
	(@RequestParam int storeId,@RequestBody Medicine medicine){
		return service.saveMedicine(storeId, medicine);
	}
	
	@PutMapping
	private ResponseEntity<ResponseStructure<Medicine>> updateMedicine(@RequestParam int medicineId, @RequestBody Medicine medicine){
		return service.updateMedicine(medicineId,medicine);
	}
	
	@GetMapping
	private ResponseEntity<ResponseStructure<Medicine>> findMedicineById(@RequestParam int medicineId){
		return service.findMedicineById(medicineId);
	}
	
	@DeleteMapping
	private ResponseEntity<ResponseStructure<Medicine>> deleteMedicine(@RequestParam int medicineId){
		return service.deleteMedicine(medicineId);
	}
}
