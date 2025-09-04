package com.formation.pharmacy_manager.repository;

import com.formation.pharmacy_manager.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
