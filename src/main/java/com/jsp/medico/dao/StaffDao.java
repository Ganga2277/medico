package com.jsp.medico.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.medico.entity.Staff;
import com.jsp.medico.repository.StaffRepo;

@Repository
public class StaffDao {
	
	@Autowired
	private StaffRepo repo;

	public Staff saveStaff(Staff staff) {
		
		return repo.save(staff);
	}


	public Staff updateStaff(int staffId, Staff staff) {
		Optional<Staff> optional = repo.findById(staffId);
		if(optional.isPresent()) {
			Staff oldStaff = optional.get();
			staff.setStaffId(staffId);
			staff.setAdmin(oldStaff.getAdmin());
			staff.setMedicalStore(oldStaff.getMedicalStore());
			return repo.save(staff);
		}
		return null;
	}


	public Staff findStaffById(int staffId) {
		Optional<Staff> optional = repo.findById(staffId);
		if(optional.isPresent()) {
			return optional.get();
		}
		return null;
	}


	public Staff deleteStaff(int staffId) {
		Optional<Staff> optional = repo.findById(staffId);
		if(optional.isPresent()) {
			Staff staff = optional.get();
			repo.delete(staff);
			return  staff;
		}
		return null;
	}
}
