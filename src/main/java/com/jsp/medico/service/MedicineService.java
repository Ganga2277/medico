package com.jsp.medico.service;

import org.hibernate.dialect.DB2390Dialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.jsp.medico.dao.MedicalStoreDao;
import com.jsp.medico.dao.MedicineDao;
import com.jsp.medico.entity.MedicalStore;
import com.jsp.medico.entity.Medicine;
import com.jsp.medico.exception.MedicalStoreIdNotFoundException;
import com.jsp.medico.exception.MedicineIdNotFoundException;
import com.jsp.medico.util.ResponseStructure;

@Service
public class MedicineService {
	
	@Autowired
	private MedicineDao medicineDao;
	
	@Autowired
	private MedicalStoreDao storeDao;

	public ResponseEntity<ResponseStructure<Medicine>> saveMedicine(int storeId, Medicine medicine) {
		MedicalStore dbMedicalStore=storeDao.getMedicalStoreById(storeId);
		if(dbMedicalStore!=null) {
			medicine.setMedicalstore(dbMedicalStore);
			Medicine dbMedicine=medicineDao.saveMedicine(medicine);
			
			ResponseStructure<Medicine> structure=new ResponseStructure<Medicine>();
			structure.setMessage("medicine added successfully");
			structure.setStatus(HttpStatus.CREATED.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>>(structure,HttpStatus.CREATED);
			
		}else {
			throw new MedicalStoreIdNotFoundException("Sorry failed to add medicine");
		}
	}



	public ResponseEntity<ResponseStructure<Medicine>> updateMedicine(int medicineId, Medicine medicine) {
		Medicine dbMedicine = medicineDao.updateMedicine(medicineId , medicine);
		if(dbMedicine!=null) {
			ResponseStructure<Medicine> structure = new ResponseStructure<Medicine>();
			structure.setMessage("Medicine updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>> (structure , HttpStatus.OK);	 
		}else {
			throw new MedicineIdNotFoundException("Sorry to failed update medicine");
		}
	}



	public ResponseEntity<ResponseStructure<Medicine>> findMedicineById(int medicineId) {
		Medicine dbMedicine = medicineDao.findMedicineById(medicineId);
		if(dbMedicine!=null) {
			ResponseStructure<Medicine> structure = new ResponseStructure<Medicine>();
			structure.setMessage("Medicine Fetched Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>> (structure , HttpStatus.FOUND);	 
		}else {
			throw new MedicineIdNotFoundException("Sorry to failed fetch medicine");
		}
	}



	public ResponseEntity<ResponseStructure<Medicine>> deleteMedicine(int medicineId) {
		Medicine dbMedicine = medicineDao.deleteMedicine(medicineId);
		if(dbMedicine!=null) {
			ResponseStructure<Medicine> structure = new ResponseStructure<Medicine>();
			structure.setMessage("Medicine Deleted Successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(dbMedicine);
			return new ResponseEntity<ResponseStructure<Medicine>> (structure , HttpStatus.GONE);	 
		}else {
			throw new MedicineIdNotFoundException("Sorry to failed delete medicine");
		}
	}

	

}
