package com.jsp.medico.entity;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Setter
@Getter
public class Admin {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int adminId;
	 private String adminName;
	 private String adminEmail;
	 private String password;
	 private long phoneNumber;
	
}
