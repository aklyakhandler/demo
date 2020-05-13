package ru.sovcombank.demo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;

@Data
@Table("card")
public class Card {
    @Id
    private Long id;
    private String number;
    private PaymentSystem paymentSystem;
    private String cardHolder;
    private LocalDate expiryDate;
    private Long accountId;
    private Long userId;

    public enum PaymentSystem {
        VISA, MASTERCARD, MIR
    }
}
