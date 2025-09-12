package com.formation.pharmacy_manager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.pharmacy_manager.dto.paymentDto.PaymentRequestDto;
import com.formation.pharmacy_manager.dto.paymentDto.PaymentResponseDto;
import com.formation.pharmacy_manager.entities.Payment;
import com.formation.pharmacy_manager.services.paymentService.PaymentService;

@RestController
@RequestMapping("/api/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public ResponseEntity<PaymentResponseDto> createPayment(@RequestBody PaymentRequestDto paymentRequestDto){
        return ResponseEntity.ok(paymentService.createPayment(paymentRequestDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Payment>> getCommand(@PathVariable Long CommandId) {
        return ResponseEntity.ok(paymentService.findByCommand(CommandId));
    }

    // @GetMapping("/{email}")
    // public ResponseEntity<List<Payment>> getPatient(@PathVariable String email){
    //     return ResponseEntity.ok(paymentService.findByPatient(email));
    // } 
}

