package com.formation.pharmacy_manager.dto.billDto;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BillRequestDto {
    long paymentId;
    String paymentMethod;
}
