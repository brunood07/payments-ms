package br.com.brunood.payments.factories;

import br.com.brunood.payments.dtos.CreatePaymentDTO;
import br.com.brunood.payments.dtos.CreditCardDTO;
import br.com.brunood.payments.entities.Payments;
import br.com.brunood.payments.enums.PaymentStatus;
import br.com.brunood.payments.enums.PaymentType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentFactoryTest {

    public static Payments payment() {

        return Payments.builder()
                .clientDocument("11111111111")
                .createdAt(LocalDateTime.now())
                .description(null)
                .installments(1)
                .orderId(1L)
                .paymentId(1L)
                .paymentStatus(PaymentStatus.PROCESSING.getValue())
                .paymentType(PaymentType.CREDIT_CARD.getValue())
                .updatedAt(LocalDateTime.now())
                .value(new BigDecimal(1000))
                .build();
    }

    public static CreatePaymentDTO createPaymentPayload() {

        return CreatePaymentDTO.builder()
                .cardInfo(null)
                .clientDocument("11111111111")
                .installments(1)
                .orderId(1L)
                .paymentType(PaymentType.PIX.getValue())
                .value(new BigDecimal(1000))
                .build();
    }

    public static CreatePaymentDTO createPaymentPayloadWithCard() {

        var card = CreditCardDTO.builder()
                .cardCvv("123")
                .cardExpirationDate("11/25")
                .cardNumber("111111111111")
                .cardName("Test Name")
                .build();

        return CreatePaymentDTO.builder()
                .cardInfo(card)
                .clientDocument("11111111111")
                .installments(1)
                .orderId(1L)
                .paymentType(PaymentType.CREDIT_CARD.getValue())
                .value(new BigDecimal(1000))
                .build();
    }
}
