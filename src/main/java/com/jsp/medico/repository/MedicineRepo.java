package com.jsp.medico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.medico.entity.Medicine;

public interface MedicineRepo extends JpaRepository<Medicine, Integer>{

}
