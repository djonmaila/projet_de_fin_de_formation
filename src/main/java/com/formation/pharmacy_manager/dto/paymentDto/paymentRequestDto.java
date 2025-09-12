package com.formation.pharmacy_manager.dto.paymentDto;

import lombok.AllArgsConstructor;
// import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data

public class PaymentRequestDto {
    private double totalAmount;
    private String paymentMethod;
    private long commandId;
}
