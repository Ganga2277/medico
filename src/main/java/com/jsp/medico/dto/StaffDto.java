package com.jsp.medico.dto;

import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StaffDto {

	private int staffId;
	  private String staffName;
	  private String email;
	  private long phoneNumber; 
	  
	  @ManyToOne
	  private AdminDto adminDto;
	  
	  @OneToOne
	  private MedicalStoreDto medicalaStoreDto;
}
