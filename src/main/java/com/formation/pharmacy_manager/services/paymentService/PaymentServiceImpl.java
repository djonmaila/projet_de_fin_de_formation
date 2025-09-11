package com.formation.pharmacy_manager.services.paymentService;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.pharmacy_manager.dto.paymentDto.PaymentRequestDto;
import com.formation.pharmacy_manager.dto.paymentDto.PaymentResponseDto;
import com.formation.pharmacy_manager.entities.Command;
import com.formation.pharmacy_manager.entities.Payment;
import com.formation.pharmacy_manager.repository.CommandRepository;
import com.formation.pharmacy_manager.repository.PaymentRepository;

import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@AllArgsConstructor
@Data
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CommandRepository commandRepository;

    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        // Fetch the associated command
        Command command = commandRepository.findById(paymentRequestDto.getCommandId())
                .orElseThrow(() -> new RuntimeException("Command not found with ID: " + paymentRequestDto.getCommandId()));
                
                Payment payment = new Payment();
                payment.setTotalAmount(paymentRequestDto.getTotalAmount());
                payment.setPaymentMethod(paymentRequestDto.getPaymentMethod());
                payment.setTotalAmount(paymentRequestDto.getTotalAmount());
                payment.setPaymentStatus("PENDING");
                payment.setCommand(command);
                

                Payment savPayment = paymentRepository.save(payment);
                PaymentResponseDto paymentResponseDto = new PaymentResponseDto();
                paymentResponseDto.setPaymentId(savPayment.getPaymentId());
                paymentResponseDto.setPaymentMethod(savPayment.getPaymentMethod());
                paymentResponseDto.setTotalAmount(savPayment.getTotalAmount());
                paymentResponseDto.setPaymentStatus(savPayment.getPaymentStatus());
                paymentResponseDto.setCommandId(savPayment.getCommand().getCommandId());
                paymentResponseDto.setPaymentDate(savPayment.getPaymentDate());

                return paymentResponseDto;
    }

    @Override
    public Optional<Payment> getPaymentById(long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public List<Payment> getPaymentsByStatus(String status) {
        return paymentRepository.findByPaymentStatus(status);
    }

    @Override
    public List<Payment> getPaymentsByMethod(String method) {
        return paymentRepository.searchByPaymentMethod(method);
    }

    @Override
    public List<Payment> findByCommand(Long commandId) {
        return paymentRepository.findByCommand(commandId);
    }

    // @Override
    // public List<Payment> findByPatient(String email) {
    //     return paymentRepository.findByPatient(email);
    // }

}

