package com.jsp.medico.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.medico.dao.BookingDao;
import com.jsp.medico.dao.CustomerDao;
import com.jsp.medico.dao.MedicineDao;
import com.jsp.medico.dto.BookingDto;
import com.jsp.medico.entity.Booking;
import com.jsp.medico.entity.Customer;
import com.jsp.medico.entity.Medicine;
import com.jsp.medico.enums.BookingStatus;
import com.jsp.medico.exception.BookingAlreadyCancelled;
import com.jsp.medico.exception.BookingCantCancelledNow;
import com.jsp.medico.exception.BookingDeliveredException;
import com.jsp.medico.exception.BookingIdNotFoundException;
import com.jsp.medico.exception.CustomerIdNotFoundException;
import com.jsp.medico.exception.MedicineIdNotFoundException;
import com.jsp.medico.util.ResponseStructure;

@Service
public class BookingService {
	
	@Autowired
	private BookingDao bookingDao;
	
	@Autowired
	private ModelMapper moddMapper;
	
	@Autowired
	private CustomerDao customerDao;
	
	@Autowired
	private MedicineDao medicineDao;

	public ResponseEntity<ResponseStructure<Booking>> saveBooking(int customerId, int medicineId,
			BookingDto bookingDto) {
		Booking booking = this.moddMapper.map(bookingDto, Booking.class);
		Customer dbCustomer = customerDao.findCustomerById(customerId);
		if (dbCustomer != null) {
//			customer is present
			Medicine dbMedicine = medicineDao.findMedicineById(medicineId);
			if (dbMedicine != null) {
//			medicine is present

				List<Medicine> medicines = new ArrayList<Medicine>();
				medicines.add(dbMedicine);
				booking.setCustomer(dbCustomer);
				booking.setMedicines(medicines);

//			update customer also
				List<Booking> bookings = new ArrayList<Booking>();
				bookings.add(booking);
				dbCustomer.setBookings(bookings);
				customerDao.updateCustomer(customerId, dbCustomer);
//				updating stock quantity
				dbMedicine.setStockQuantity(dbMedicine.getStockQuantity()-booking.getQuantity());
//				im adding booking status
				booking.setBookingStatus(BookingStatus.ACTIVE);
				
				Booking dbBooking = bookingDao.saveBooking(booking);

				
				ResponseStructure<Booking> structure = new ResponseStructure<Booking>();
				structure.setMessage("Booking added successfully");
				structure.setStatus(HttpStatus.CREATED.value());
				structure.setData(dbBooking);
				return new ResponseEntity<ResponseStructure<Booking>>(structure, HttpStatus.CREATED);

			} else {
				throw new MedicineIdNotFoundException("Sorry failed to add booking");
			}

		} else {
			throw new CustomerIdNotFoundException("Sorry failed to add booking");
		}
	}

	public ResponseEntity<ResponseStructure<Booking>> cancelBooking(int bookingId) {
           Booking dbBooking=bookingDao.findBookingById(bookingId);

//		Expected date=24
//		cantcancelleddate=24-2=22;
		
		if(dbBooking!=null) {
			LocalDate cantcancelledday=dbBooking.getExpecteDate().minusDays(2);
           if(dbBooking.getBookingStatus().equals(BookingStatus.CANCELLED)) {
        	   throw new BookingAlreadyCancelled("sorry this booking already Cancelled");
           }else if(dbBooking.getBookingStatus().equals(BookingStatus.DELIVERED)) {
        	   throw new BookingDeliveredException("Sorry cant cancel Booking its already delivered");
           }else if(LocalDate.now().equals(cantcancelledday)||LocalDate.now().isAfter(cantcancelledday)) {
       			throw new BookingCantCancelledNow("Sorry booking cant cancelled now because expecteddate is near");
           }else {
        	   Booking cancelledBooking=bookingDao.cancelBooking(bookingId);
        	   ResponseStructure<Booking> structure=new ResponseStructure<Booking>();
        	   structure.setMessage("Booking cancelled Successfully");
       	   structure.setStatus(HttpStatus.OK.value());
        	   structure.setData(cancelledBooking);
               return new ResponseEntity<ResponseStructure<Booking>>(structure,HttpStatus.OK);       	   
           }
	
		}else {
			throw new BookingIdNotFoundException("Sorry failed to cancel booking");
		}
	
	}

	

}
