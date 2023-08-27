package com.jsp.medico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.jsp.medico.util.ResponseStructure;

@RestControllerAdvice
public class MedicoExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressIdNotFoundException(AddressIdNotFoundException  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("AddressId Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> adminIdNotFoundException(AdminIdNotFoundException  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("AdminId Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> storeIdNotFoundException(MedicalStoreIdNotFoundException  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("StoreID Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);		
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> staffIdNotFoundException(StaffIdNotFoundException  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("StaffId Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> medicineIdNotFoundException(MedicineIdNotFoundException  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("MedicineId Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> customerIdNotFoundException(CustomerIdNotFoundException  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("CustomerId Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressAlreadyMapped(AddressAlreadyMappedToOtherCustomer  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Address Already Mapped To Other Customer");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressAlreadyMapped(AddressAlreadymappedToCustomer  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Address Already Mapped To Customer");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> addressAlreadyMapped(AddressMappedToMedicalStore  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Address Already Mapped To Customer");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingIdNotFound(BookingIdNotFoundException  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("BookingId Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingCantCancelledNow(BookingCantCancelledNow  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Booking Can't Cancelled now");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingAlreadyCancelledException(BookingAlreadyCancelled  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Booking Cancelled Already");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> bookingDeliveredException(BookingDeliveredException  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Booking Delivered Already");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> emailNotFoundException(EmailNotFoundException  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("EmailId Not Found");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> invalidPasswordException(InvalidPasswordException  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("Password is invalid");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);	
	}
	
	@ExceptionHandler
	public ResponseEntity<ResponseStructure<String>> PhoneNumberInvalidException(PhoneNumberNotValidException  ex){
		ResponseStructure<String> structure = new ResponseStructure<String>();
		structure.setMessage("PhoneNumber is invalid");
		structure.setStatus(HttpStatus.NOT_FOUND.value());
		structure.setData(ex.getMessage());
		return new ResponseEntity<ResponseStructure<String>>(structure , HttpStatus.NOT_FOUND);	
	}
}
