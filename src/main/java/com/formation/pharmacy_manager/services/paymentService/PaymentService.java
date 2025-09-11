package com.formation.pharmacy_manager.services.paymentService;

import java.util.List;
import java.util.Optional;

import com.formation.pharmacy_manager.dto.paymentDto.PaymentRequestDto;
import com.formation.pharmacy_manager.dto.paymentDto.PaymentResponseDto;
import com.formation.pharmacy_manager.entities.Payment;

public interface PaymentService {
    PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto);

    Optional<Payment> getPaymentById(long id);

    List<Payment> getPaymentsByStatus(String status);

    List<Payment> getPaymentsByMethod(String method);
}
