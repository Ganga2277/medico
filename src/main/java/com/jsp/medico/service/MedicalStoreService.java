package com.jsp.medico.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.SqlReturnType;
import org.springframework.stereotype.Service;

import com.jsp.medico.dao.AddressDao;
import com.jsp.medico.dao.AdminDao;
import com.jsp.medico.dao.MedicalStoreDao;
import com.jsp.medico.dto.AddressDto;
import com.jsp.medico.dto.AdminDto;
import com.jsp.medico.dto.MedicalStoreDto;
import com.jsp.medico.entity.Address;
import com.jsp.medico.entity.Admin;
import com.jsp.medico.entity.MedicalStore;
import com.jsp.medico.exception.AddressAlreadymappedToCustomer;
import com.jsp.medico.exception.AddressIdNotFoundException;
import com.jsp.medico.exception.AddressMappedToMedicalStore;
import com.jsp.medico.exception.AdminIdNotFoundException;
import com.jsp.medico.exception.MedicalStoreIdNotFoundException;
import com.jsp.medico.util.ResponseStructure;

import net.bytebuddy.utility.dispatcher.JavaDispatcher.IsConstructor;

@Service
public class MedicalStoreService {

	@Autowired
	public MedicalStoreDao storeDao;
	
	@Autowired
	public ModelMapper modelMapper;
	
	@Autowired
	public AdminDao adminDao;
	
	@Autowired
	public AddressDao addressDao;


	public ResponseEntity<ResponseStructure<MedicalStoreDto>> saveMedicalStore(int adminId, int addressId,
			MedicalStoreDto medicalStoreDto) {
		MedicalStore medicalStore = this.modelMapper.map(medicalStoreDto, MedicalStore.class);
		
//		this medicalstore is not having admin and address yet
//		so first i need to get that admin
		Admin dbAdmin = adminDao.findAdminById(adminId);
//		im checking whether this admin is present or not
	
	  if(dbAdmin!=null) {
		  medicalStore.setAdmin(dbAdmin);
		  Address dbAddress = addressDao.findAddressById(addressId);
		  if(dbAddress!=null) {
	    	   if(dbAddress.getCustomer()!=null) {
	    		   throw new AddressAlreadymappedToCustomer("Sorry that address is mapped to customer so please give other address");
	    	   }
	    	   if(dbAddress.getMedicalStore()!=null) {
	    		   throw new AddressMappedToMedicalStore("Sorry address mapped to other medical store");
	    	   }
			  medicalStore.setAddress(dbAddress);
			  dbAddress.setMedicalStore(medicalStore);
			  
			  MedicalStore dbMedicalStore=storeDao.saveMedicalStore(medicalStore);
			  Address dbMedicalStoreAddress = dbMedicalStore.getAddress();
			  AddressDto addressDto = this.modelMapper.map(dbMedicalStoreAddress, AddressDto.class);
			  Admin dbMedicalStoreAdmin = dbMedicalStore.getAdmin();
			
			 MedicalStoreDto dto =this.modelMapper.map(dbMedicalStore, MedicalStoreDto.class);
			 dto.setAddressDto(addressDto);
			 dto.setAdminDto(this.modelMapper.map(dbMedicalStoreAdmin, AdminDto.class));
	    	   ResponseStructure<MedicalStoreDto> structure=new ResponseStructure<MedicalStoreDto>();
	    	   structure.setMessage("MedicalStore added successfully");
	    	   structure.setStatus(HttpStatus.CREATED.value());
	    	   structure.setData(dto);
	    	   return new ResponseEntity<ResponseStructure<MedicalStoreDto>>(structure,HttpStatus.CREATED);
	    	   
		  }else {
	    	   throw new AddressIdNotFoundException("Sorry failed to add medicalstore");
	       }
 
		
	}else {
		throw new AdminIdNotFoundException("Sorry failed to add medicalstore");
	}
	  }


	public ResponseEntity<ResponseStructure<MedicalStoreDto>> updateMedicalStore(int storeId,
			MedicalStore medicalStore) {
		MedicalStore dbMedicalStore = storeDao.updateMedicalStore(storeId, medicalStore);
		if(dbMedicalStore!=null) {
			MedicalStoreDto storeDto = this.modelMapper.map(dbMedicalStore, MedicalStoreDto.class);
			storeDto.setAddressDto(this.modelMapper.map(dbMedicalStore.getAddress(),AddressDto.class));
			storeDto.setAdminDto(this.modelMapper.map(dbMedicalStore.getAdmin(), AdminDto.class));
		  
			ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("MedicalStore Updated Successfully");
			structure.setStatus(HttpStatus.OK.value());
			structure.setData(storeDto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>> (structure , HttpStatus.OK);
		}else {
			throw new MedicalStoreIdNotFoundException("Sorry Failed to Update MedicalStore");
		}
	}


	public ResponseEntity<ResponseStructure<MedicalStoreDto>> getMedicalStoreById(int storeId) {
		MedicalStore dbMedicalStore = storeDao.getMedicalStoreById(storeId);
		if(dbMedicalStore!=null) {
			MedicalStoreDto storeDto = this.modelMapper.map(dbMedicalStore, MedicalStoreDto.class);
			storeDto.setAddressDto(this.modelMapper.map(dbMedicalStore.getAddress(),AddressDto.class));
			storeDto.setAdminDto(this.modelMapper.map(dbMedicalStore.getAdmin(), AdminDto.class));
		  
			ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("MedicalStore fetched Successfully");
			structure.setStatus(HttpStatus.FOUND.value());
			structure.setData(storeDto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>> (structure , HttpStatus.FOUND);
		}else {
			throw new MedicalStoreIdNotFoundException("Sorry Failed to fetch MedicalStore");
		}
	}


	public ResponseEntity<ResponseStructure<MedicalStoreDto>> deleteMedicalStore(int storeId) {
		MedicalStore dbMedicalStore = storeDao.deleteMedicalStore(storeId);
		if(dbMedicalStore!=null) {
			MedicalStoreDto storeDto = this.modelMapper.map(dbMedicalStore, MedicalStoreDto.class);
			storeDto.setAddressDto(this.modelMapper.map(dbMedicalStore.getAddress(),AddressDto.class));
			storeDto.setAdminDto(this.modelMapper.map(dbMedicalStore.getAdmin(), AdminDto.class));
		  
			ResponseStructure<MedicalStoreDto> structure = new ResponseStructure<MedicalStoreDto>();
			structure.setMessage("MedicalStore deleted Successfully");
			structure.setStatus(HttpStatus.GONE.value());
			structure.setData(storeDto);
			return new ResponseEntity<ResponseStructure<MedicalStoreDto>> (structure , HttpStatus.GONE);
		}else {
			throw new MedicalStoreIdNotFoundException("Sorry Failed to delete MedicalStore");
		}
	}
	
}
