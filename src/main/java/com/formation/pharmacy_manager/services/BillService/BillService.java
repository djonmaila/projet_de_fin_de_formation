package com.formation.pharmacy_manager.services.BillService;

import java.util.Optional;

import com.formation.pharmacy_manager.entities.Bill;

public interface BillService {
    
    Bill generateBill(long paymentId);
    Optional<Bill> getBillById(long id);
}
