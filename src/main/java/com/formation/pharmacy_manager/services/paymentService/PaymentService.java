package com.formation.pharmacy_manager.services.paymentService;

import java.util.List;

import com.formation.pharmacy_manager.dto.paymentDto.PaymentRequestDto;
import com.formation.pharmacy_manager.dto.paymentDto.PaymentResponseDto;
import com.formation.pharmacy_manager.entities.Payment;

public interface PaymentService {
    PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto);

    PaymentResponseDto getPaymentById(long id);

    List<Payment> findByCommand(Long commandId); 
    List<PaymentResponseDto> getAllPayments();
    void deletePayment(long paymentId);
    // List<Payment> findByPatient(String email);
    List<Payment> getPaymentsByStatus(String status);
    List<Payment> getPaymentsByMethod(String method);

    
}
