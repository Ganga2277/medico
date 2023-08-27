package com.jsp.medico.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.medico.entity.Address;
import com.jsp.medico.entity.Admin;
import com.jsp.medico.repository.AdminReop;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Repository
public class AdminDao {
	
	@Autowired
	public AdminReop repo;

	public Admin saveAdmin(Admin admin) {
		
		return repo.save(admin);
	}


	public Admin findAdminById(int adminId ) {
		java.util.Optional<Admin> optional = repo.findById(adminId);
		if(optional.isPresent())
		{
			return optional.get();
		}
		return null;
	}


	public Admin updateAdmin(int adminId, Admin admin) {
		java.util.Optional<Admin> optional=repo.findById(adminId);
		if(optional.isPresent())
		{
			admin.setAdminId(adminId);
			return repo.save(admin);
		}
		return null;
	}


	public Admin deleteAdmin(int adminId) {
		java.util.Optional<Admin> optional=repo.findById(adminId);
		if(optional.isPresent()) {
			Admin admin = optional.get();
			repo.delete(admin);
			return admin;
		}
		return null;
	}
	
	

}
