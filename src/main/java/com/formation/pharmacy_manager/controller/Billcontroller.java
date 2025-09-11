package com.formation.pharmacy_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    try {
        Bill bill = billService.generateBill(billRequest.getPaymentId(), billRequest.getPaymentMethod());
        BillResponseDto responseDto = new BillResponseDto(bill.getBillId(), bill.getPayment().getPaymentId(), bill.getCreationDate(), bill.getTotalAmount(), bill.getPayment().getPaymentMethod());
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    } catch (RuntimeException e) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
}
// bill.getCreationDate()