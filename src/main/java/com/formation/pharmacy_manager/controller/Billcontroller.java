package com.formation.pharmacy_manager.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.pharmacy_manager.dto.billDto.BillRequestDto;
import com.formation.pharmacy_manager.dto.billDto.BillResponseDto;
import com.formation.pharmacy_manager.entities.Bill;
import com.formation.pharmacy_manager.services.BillService.BillService;


@RestController
@RequestMapping("/api/bill")
public class Billcontroller {

    @Autowired
    private BillService billService;

    @PostMapping("/create")
    public ResponseEntity<BillResponseDto> createBill(@RequestBody BillRequestDto billRequest) {
        Bill bill = billService.generateBill(billRequest.getPaymentId());
        
        BillResponseDto responseDto = new BillResponseDto(
            bill.getBillId(),
            bill.getPayment().getPaymentId(),
            bill.getCreationDate(),
            bill.getTotalAmount(),
            bill.getPayment().getPaymentMethod().name());

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBillById(@PathVariable long id) {
        Optional<Bill> billOptional = billService.getBillById(id);
        
        if (billOptional.isPresent()) {
            Bill foundBill = billOptional.get();
            BillResponseDto responseDto = new BillResponseDto(
                foundBill.getBillId(),
                foundBill.getPayment().getPaymentId(),
                foundBill.getCreationDate(),
                foundBill.getTotalAmount(),
                foundBill.getPayment().getPaymentMethod().name());
            
            return ResponseEntity.ok(responseDto);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
