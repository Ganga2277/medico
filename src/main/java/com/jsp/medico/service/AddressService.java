package com.jsp.medico.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.medico.dao.AddressDao;
import com.jsp.medico.dto.AddressDto;
import com.jsp.medico.entity.Address;
import com.jsp.medico.exception.AddressIdNotFoundException;
import com.jsp.medico.util.ResponseStructure;

@Service
public class AddressService {

	@Autowired
	private AddressDao addressDao;

	public ResponseEntity<ResponseStructure<AddressDto>> saveAddress(Address address) {
		Address dbAddress = addressDao.saveAddress(address);
		AddressDto dto= new AddressDto();
		dto.setAddressId(dbAddress.getAddressId());
		dto.setCity(dbAddress.getCity());
		dto.setPincode(dbAddress.getPincode());
		dto.setState(dbAddress.getState());
		dto.setStreetName(dbAddress.getStreetName());
		ResponseStructure<AddressDto> structure = new ResponseStructure<AddressDto>();
		structure.setMessage("Address Saved SuccessFully");
		structure.setStatus(HttpStatus.CREATED.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.CREATED);
	}
	
	public ResponseEntity<ResponseStructure<AddressDto>> findAddressById(int addressId) {
		Address dbAddress = addressDao.findAddressById(addressId);
		if(dbAddress!=null) {
		AddressDto dto= new AddressDto();
		dto.setAddressId(dbAddress.getAddressId());
		dto.setCity(dbAddress.getCity());
		dto.setPincode(dbAddress.getPincode());
		dto.setState(dbAddress.getState());
		dto.setStreetName(dbAddress.getStreetName());
		ResponseStructure<AddressDto> structure = new ResponseStructure<AddressDto>();
		structure.setMessage("AddressData Saved SuccessFully");
		structure.setStatus(HttpStatus.FOUND.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.FOUND);
		}
		else {
			throw new AddressIdNotFoundException("Sorry failed to fetch the data");
		}
	}
	

	public ResponseEntity<ResponseStructure<AddressDto>> updateAddress(int addressId, Address address) {
		Address dbAddress = addressDao.updateAddress(addressId , address);
		if(dbAddress!=null) {
		AddressDto dto= new AddressDto();
		dto.setAddressId(dbAddress.getAddressId());
		dto.setCity(dbAddress.getCity());
		dto.setPincode(dbAddress.getPincode());
		dto.setState(dbAddress.getState());
		dto.setStreetName(dbAddress.getStreetName());
		
		ResponseStructure<AddressDto> structure = new ResponseStructure<AddressDto>();
		structure.setMessage("Address updated SuccessFully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.OK);
		}
		else {
			throw new AddressIdNotFoundException("Sorry failed to fetch the data");
		}
	}

	public ResponseEntity<ResponseStructure<AddressDto>> deleteAddress(int addressId) {
		Address dbAddress = addressDao.deleteAddressById(addressId);
		if(dbAddress!=null) {
		AddressDto dto= new AddressDto();
		dto.setAddressId(dbAddress.getAddressId());
		dto.setCity(dbAddress.getCity());
		dto.setPincode(dbAddress.getPincode());
		dto.setState(dbAddress.getState());
		dto.setStreetName(dbAddress.getStreetName());
		
		ResponseStructure<AddressDto> structure = new ResponseStructure<AddressDto>();
		structure.setMessage("Address deleted SuccessFully");
		structure.setStatus(HttpStatus.OK.value());
		structure.setData(dto);
		return new ResponseEntity<ResponseStructure<AddressDto>>(structure,HttpStatus.OK);
		}
		else {
//			raise exception 
			throw new AddressIdNotFoundException("Sorry failed to fetch the data");
		}
	}
	
}
