package com.formation.pharmacy_manager.dto.paymentDto;

// import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data

public class PaymentRequestDto {
    private double totalAmount;
    private String paymentMethod;
    private long commandId;

    public PaymentRequestDto(double totalAmount, String paymentMethod, long commandId)
    {
        this.totalAmount = totalAmount;
        this.paymentMethod = paymentMethod;
        this.commandId = commandId;
    }

    
}
