package com.formation.pharmacy_manager.dto.billDto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BillResponseDto {

    long billId;
    long paymentId;
    LocalDate billDate;
    double totalAmount;
    String paymentMethod;
}
