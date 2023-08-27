package com.jsp.medico.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.medico.entity.Medicine;
import com.jsp.medico.repository.MedicineRepo;

@Repository
public class MedicineDao {
	
	@Autowired
	private MedicineRepo repo;

	public Medicine saveMedicine(Medicine medicine) {
		
		return repo.save(medicine);
	}
	
	
	public Medicine updateMedicine(int medicineId, Medicine medicine) {
		Optional<Medicine> optional = repo.findById(medicineId);
		if(optional.isPresent()) {
			medicine.setMedicineId(medicineId);
			medicine.setMedicalstore(medicine.getMedicalstore());
			return repo.save(medicine);
		}
		return null;
	}


	public Medicine findMedicineById(int medicineId) {
		Optional<Medicine> optional = repo.findById(medicineId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}


	public Medicine deleteMedicine(int medicineId) {
		Optional<Medicine> optional = repo.findById(medicineId);
		if(optional.isPresent()) {
		Medicine medicine = optional.get();
		repo.delete(medicine);
		return medicine;
		}
		return null;
	}

}
