package br.com.brunood.payments.dtos;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

public class CreditCardDTO {

    private String cardNumber;
    private String cardCvv;
    private String cardName;
    private String cardExpirationDate;
}
