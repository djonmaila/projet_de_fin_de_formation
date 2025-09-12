package com.formation.pharmacy_manager.services.paymentService;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.formation.pharmacy_manager.dto.paymentDto.PaymentRequestDto;
import com.formation.pharmacy_manager.dto.paymentDto.PaymentResponseDto;
import com.formation.pharmacy_manager.entities.Command;
import com.formation.pharmacy_manager.entities.Payment;
import com.formation.pharmacy_manager.entities.PaymentStatus;
import com.formation.pharmacy_manager.repository.CommandRepository;
import com.formation.pharmacy_manager.repository.PaymentRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Data;

@Service
@AllArgsConstructor
@Data
@Transactional
public class PaymentServiceImpl implements PaymentService{
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private CommandRepository commandRepository;

    @Override
    public PaymentResponseDto createPayment(PaymentRequestDto paymentRequestDto) {
        // Logique pour trouver le prix total de la commande
        Command command = commandRepository.findById(paymentRequestDto.getCommandId())
                .orElseThrow(() -> new RuntimeException("Command not found with id: " + paymentRequestDto.getCommandId()));

        // TODO: Calculer le totalAmount en fonction des médicaments de la commande
        double totalAmount = 0.0; // Ceci est un exemple, remplacez par votre logique

        Payment payment = new Payment();
        payment.setCommand(command);
        payment.setTotalAmount(totalAmount);
        payment.setPaymentMethod(paymentRequestDto.getPaymentMethod());
        payment.setPaymentStatus(PaymentStatus.PENDING); // Initialise le statut à PENDING
        payment.setPaymentDate(LocalDate.now());

        Payment savedPayment = paymentRepository.save(payment);

        return new PaymentResponseDto(
                savedPayment.getPaymentId(),
                savedPayment.getTotalAmount(),
                savedPayment.getPaymentMethod(),
                savedPayment.getPaymentStatus(),
                command.getCommandId(),
                savedPayment.getPaymentDate()
        );
    }

    @Override
    public PaymentResponseDto getPaymentById(long paymentId) {
        Payment payment = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new RuntimeException("Payment not found with id: " + paymentId));
        return new PaymentResponseDto(
                payment.getPaymentId(),
                payment.getTotalAmount(),
                payment.getPaymentMethod(),
                payment.getPaymentStatus(),
                payment.getCommand().getCommandId(),
                payment.getPaymentDate()
        );
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

    @Override
    public List<PaymentResponseDto> getAllPayments() {
        List<Payment> payments = paymentRepository.findAll();
        return payments.stream()
                .map(payment -> new PaymentResponseDto(
                        payment.getPaymentId(),
                        payment.getTotalAmount(),
                        payment.getPaymentMethod(),
                        payment.getPaymentStatus(),
                        payment.getCommand().getCommandId(),
                        payment.getPaymentDate()))
                .collect(Collectors.toList());
    }

    @Override
    public void deletePayment(long paymentId) {
        paymentRepository.deleteById(paymentId);
    }

    // @Override
    // public List<Payment> findByPatient(String email) {
    //     return paymentRepository.findByPatient(email);
    // }

}

