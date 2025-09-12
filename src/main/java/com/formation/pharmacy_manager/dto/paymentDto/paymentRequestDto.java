package com.formation.pharmacy_manager.dto.paymentDto;

import com.formation.pharmacy_manager.entities.PaymentMethod;

import lombok.AllArgsConstructor;
// import lombok.AllArgsConstructor;
import lombok.Data;




@Data
@AllArgsConstructor
public class PaymentRequestDto {
    private long commandId;
    private PaymentMethod paymentMethod;
}
