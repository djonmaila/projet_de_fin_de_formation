package com.formation.pharmacy_manager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.formation.pharmacy_manager.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

}
