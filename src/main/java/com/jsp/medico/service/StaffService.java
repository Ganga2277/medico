package com.jsp.medico.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.medico.dao.AdminDao;
import com.jsp.medico.dao.MedicalStoreDao;
import com.jsp.medico.dao.StaffDao;
import com.jsp.medico.dto.AdminDto;
import com.jsp.medico.dto.MedicalStoreDto;
import com.jsp.medico.dto.StaffDto;
import com.jsp.medico.entity.Admin;
import com.jsp.medico.entity.MedicalStore;
import com.jsp.medico.entity.Staff;
import com.jsp.medico.exception.AddressIdNotFoundException;
import com.jsp.medico.exception.MedicalStoreIdNotFoundException;
import com.jsp.medico.exception.StaffIdNotFoundException;
import com.jsp.medico.util.ResponseStructure;

@Service
public class StaffService {
	
	@Autowired
	private StaffDao staffDao;
	
	@Autowired
	private MedicalStoreDao medicalStoreDao;
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	private ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<StaffDto>> saveStaff(int adminId, int storeId, Staff staff) {
	    MedicalStore dbMedicalStore = medicalStoreDao.getMedicalStoreById(storeId);
	    if(dbMedicalStore!=null) {
	    	staff.setMedicalStore(dbMedicalStore);
	    	Admin dbAdmin = adminDao.findAdminById(adminId);
	    	if(dbAdmin!=null) {
	    		staff.setAdmin(dbAdmin);
	    		Staff dbStaff = staffDao.saveStaff(staff);
	    		StaffDto staffDto = this.modelMapper.map(dbStaff, StaffDto.class);
	    		staffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class));
	    		staffDto.setMedicalaStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class));
	    		ResponseStructure<StaffDto> structure = new ResponseStructure<StaffDto>();
	    		structure.setMessage("Staff Data Saved Successfully ");
	    		structure.setStatus(HttpStatus.CREATED.value());
	    		structure.setData(staffDto);
	    		return new ResponseEntity<ResponseStructure<StaffDto>> (structure , HttpStatus.CREATED);
	    			
	    	}else {
				throw new AddressIdNotFoundException("Sorry Failed to Add Staff");
			}	
	    }else {
	    	throw new MedicalStoreIdNotFoundException("Sorry Failed to Add Staff");
	    }
	}

	public ResponseEntity<ResponseStructure<StaffDto>> updateStaff(int staffId, Staff staff) {
	    		Staff dbStaff = staffDao.updateStaff(staffId,staff);
	    		if(dbStaff!=null) {
	    		StaffDto staffDto = this.modelMapper.map(dbStaff, StaffDto.class);
	    		staffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class));
	    		staffDto.setMedicalaStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class));
	    		ResponseStructure<StaffDto> structure = new ResponseStructure<StaffDto>();
	    		structure.setMessage("Staff Data updated Successfully ");
	    		structure.setStatus(HttpStatus.OK.value());
	    		structure.setData(staffDto);
	    		return new ResponseEntity<ResponseStructure<StaffDto>> (structure , HttpStatus.OK);
	    			
	    	}else {
				throw new StaffIdNotFoundException("Sorry Failed to update Staff");
			}	
	   
	}

	public ResponseEntity<ResponseStructure<StaffDto>> findStaffById(int staffId) {
		Staff dbStaff = staffDao.findStaffById(staffId);
		if(dbStaff!=null) {
		StaffDto staffDto = this.modelMapper.map(dbStaff, StaffDto.class);
		staffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class));
		staffDto.setMedicalaStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class));
		ResponseStructure<StaffDto> structure = new ResponseStructure<StaffDto>();
		structure.setMessage("Staff Data fetched Successfully ");
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setData(staffDto);
		return new ResponseEntity<ResponseStructure<StaffDto>> (structure , HttpStatus.FOUND);
			
	}else {
		throw new StaffIdNotFoundException("Sorry Failed to fetch Staff");
	}	
	}

	public ResponseEntity<ResponseStructure<StaffDto>> deleteStaff(int staffId) {
		Staff dbStaff = staffDao.deleteStaff(staffId);
		if(dbStaff!=null) {
		StaffDto staffDto = this.modelMapper.map(dbStaff, StaffDto.class);
		staffDto.setAdminDto(this.modelMapper.map(dbStaff.getAdmin(), AdminDto.class));
		staffDto.setMedicalaStoreDto(this.modelMapper.map(dbStaff.getMedicalStore(), MedicalStoreDto.class));
		ResponseStructure<StaffDto> structure = new ResponseStructure<StaffDto>();
		structure.setMessage("Staff Data Deleted Successfully ");
		structure.setStatus(HttpStatus.GONE.value());
		structure.setData(staffDto);
		return new ResponseEntity<ResponseStructure<StaffDto>> (structure , HttpStatus.GONE);
			
	}else {
		throw new StaffIdNotFoundException("Sorry Failed to delete Staff");
	}	
	}

}
