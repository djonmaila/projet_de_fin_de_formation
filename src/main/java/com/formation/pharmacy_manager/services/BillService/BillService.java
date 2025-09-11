package com.formation.pharmacy_manager.services.BillService;

import java.util.Optional;

import com.formation.pharmacy_manager.dto.billDto.BillRequestDto;
import com.formation.pharmacy_manager.dto.billDto.BillResponseDto;
import com.formation.pharmacy_manager.entities.Bill;

public interface BillService {
    
    BillResponseDto createBill(BillRequestDto billDto);
    Bill generateBill(long paymentId, String paymentMethod);
    Optional<Bill> getBillById(long id);
}
