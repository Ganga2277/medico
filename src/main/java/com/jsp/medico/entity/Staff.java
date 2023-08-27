package com.jsp.medico.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Staff {
      
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	  private int staffId;
	  private String staffName;
	  private String email;
	  private String password;
	  private long phoneNumber; 
	  
	  @ManyToOne
	  private Admin admin;
	  
	  @OneToOne
	  private MedicalStore medicalStore;
	  
}
