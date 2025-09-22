package com.formation.pharmacy_manager.repository;

import com.formation.pharmacy_manager.entities.Pharmacist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PharmacistRepository extends JpaRepository<Pharmacist,Long> {
}
