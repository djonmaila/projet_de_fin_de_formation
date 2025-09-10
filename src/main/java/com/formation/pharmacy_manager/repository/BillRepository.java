package com.formation.pharmacy_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.formation.pharmacy_manager.entities.Bill;

public interface BillRepository extends JpaRepository<Bill, Long>{
    
}
