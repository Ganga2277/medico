package com.jsp.medico.service;

import javax.xml.crypto.Data;

import org.apache.catalina.authenticator.SavedRequest;
import org.hibernate.loader.AbstractEntityJoinWalker;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.jsp.medico.dao.AdminDao;
import com.jsp.medico.dto.AdminDto;
import com.jsp.medico.entity.Admin;
import com.jsp.medico.exception.AddressIdNotFoundException;
import com.jsp.medico.exception.AdminIdNotFoundException;
import com.jsp.medico.repository.AdminReop;
import com.jsp.medico.util.ResponseStructure;

@Service
public class AdminService {
	
	@Autowired
	private AdminDao adminDao;
	
	@Autowired
	public ModelMapper modelMapper;

	public ResponseEntity<ResponseStructure<AdminDto>> saveAdmin(Admin admin) {
		Admin dbAdmin = adminDao.saveAdmin(admin);
		AdminDto adminDto = this.modelMapper.map(dbAdmin, AdminDto.class);
		ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
		
		structure.setMessage("Admin Data Saved Successfully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(adminDto);
		return new ResponseEntity<ResponseStructure<AdminDto>>(structure , HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<AdminDto>> findAdminById(int adminId) {
		Admin dbAdmin = adminDao.findAdminById(adminId);
		if(dbAdmin!=null) {
			AdminDto adminDto = this.modelMapper.map(dbAdmin, AdminDto.class);
			ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
			structure.setMessage("AdminData Saved Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(adminDto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.FOUND);
		}else {
			throw new AdminIdNotFoundException("Sorry failed to fetch the data");
		}
	}

	public ResponseEntity<ResponseStructure<AdminDto>> updateAdmin(int adminId, Admin admin) {
		Admin dbAdmin = adminDao.updateAdmin(adminId , admin);
		if(dbAdmin!=null) {
			AdminDto adminDto = this.modelMapper.map(dbAdmin, AdminDto.class);
			ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
			structure.setMessage("Admin Data updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(adminDto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}else {
			return null;
		}
	}

	public ResponseEntity<ResponseStructure<AdminDto>> deleteAdmin(int adminId) {
		Admin dbAdmin = adminDao.deleteAdmin(adminId);
		if(dbAdmin!=null) {
			AdminDto adminDto = this.modelMapper.map(dbAdmin, AdminDto.class);
			ResponseStructure<AdminDto> structure = new ResponseStructure<AdminDto>();
			structure.setMessage("Admin deleted Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(adminDto);
			return new ResponseEntity<ResponseStructure<AdminDto>>(structure,HttpStatus.OK);
		}else {
			return null;
		}
	}
	
	
	
	
	

}
