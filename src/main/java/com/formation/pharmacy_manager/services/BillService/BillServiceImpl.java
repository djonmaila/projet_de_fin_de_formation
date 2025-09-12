package com.formation.pharmacy_manager.services.BillService;


import java.time.LocalDate;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.formation.pharmacy_manager.entities.Bill;
import com.formation.pharmacy_manager.entities.Payment;
import com.formation.pharmacy_manager.entities.PaymentStatus;
import com.formation.pharmacy_manager.exceptions.PaymentException;
import com.formation.pharmacy_manager.repository.BillRepository;
import com.formation.pharmacy_manager.repository.PaymentRepository;

@Service

public class BillServiceImpl implements BillService{

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Override
    public Bill generateBill(long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new PaymentException("No payment found with id: " + paymentId));

        if (payment.getPaymentStatus() != PaymentStatus.FINISHED) {
            throw new PaymentException("Cannot generate bill for an unfinished payment.");
        }

        Bill bill = new Bill();
        bill.setPayment(payment);
        bill.setCreationDate(LocalDate.now());
        bill.setTotalAmount(payment.getTotalAmount());
        
        return billRepository.save(bill);
    }

    @Override
    public Optional<Bill> getBillById(long id) {
        return billRepository.findById(id);
    }

    

}
