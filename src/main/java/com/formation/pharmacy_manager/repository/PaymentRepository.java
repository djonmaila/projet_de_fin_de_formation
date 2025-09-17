package com.formation.pharmacy_manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import com.formation.pharmacy_manager.entities.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long>{

    //all command payment
    @Query("SELECT p FROM Payment p WHERE p.command.commandId = :commandId ORDER BY p.paymentDate DESC")
    List<Payment> findByCommand(@Param("command") Long commandId);

    @Query("SELECT p FROM Payment p WHERE p.paymentStatus = :status")
    List<Payment> findByPaymentStatus(@Param("status") String paymentStatus);
    
    @Query("SELECT p FROM Payment p WHERE p.paymentMethod = :method")
    List<Payment> searchByPaymentMethod(@Param("method") String paymentMethod);

    
    Optional<Payment> findByCommand_CommandId(long commandId);

}
