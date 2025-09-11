package com.formation.pharmacy_manager.dto.paymentDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentResponseDto {
    private long paymentId;
    private double totalAmount;
    private String paymentMethod;
    private String paymentStatus;
    private long commandId;
}
