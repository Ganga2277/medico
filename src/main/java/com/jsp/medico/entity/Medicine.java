package com.jsp.medico.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.PackagePrivate;

@Entity
@Setter
@Getter
public class Medicine {

	@Id
	
	private int medicineId;
	private String medicineName;
	private double cost;
	private LocalDate expiryDate;
	private int stockQuantity;
	private String manufacturer;
	private String description;
	
	@ManyToOne
	private MedicalStore medicalstore;
}
