package com.jsp.medico.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.sym.Name;
import com.jsp.medico.entity.MedicalStore;
import com.jsp.medico.repository.MedicalStoreRepo;

@Repository
public class MedicalStoreDao {

	@Autowired
	public MedicalStoreRepo repo;

	public MedicalStore saveMedicalStore(MedicalStore medicalStore) {
		
		return repo.save(medicalStore);
	}

	public MedicalStore updateMedicalStore(int storeId, MedicalStore medicalStore) {
//		medicalstore = Name , managername , phone
		Optional<MedicalStore> optional = repo.findById(storeId);
		if(optional.isPresent()) {
			MedicalStore oldMedicalStore = optional.get();
            medicalStore.setStoreId(storeId);
//      medicalstore = id, Name , managername , phone
            medicalStore.setAdmin(oldMedicalStore.getAdmin());
            medicalStore.setAddress(oldMedicalStore.getAddress());
            return repo.save(medicalStore);
		}
		return null;
	}

	public MedicalStore getMedicalStoreById(int storeId) {
		if(repo.findById(storeId).isPresent()){
			return repo.findById(storeId).get();
		}
		return null;
	}

	public MedicalStore deleteMedicalStore(int storeId) {
		Optional<MedicalStore> optional = repo.findById(storeId);
		if (optional.isPresent()) {
			MedicalStore medicalStore = optional.get();
			repo.delete(medicalStore);
			return medicalStore;	
		}
		return null;
	}
}
