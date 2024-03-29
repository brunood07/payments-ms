package br.com.brunood.payments.usecases;

import br.com.brunood.payments.dtos.CreatePaymentDTO;
import br.com.brunood.payments.entities.Payments;
import br.com.brunood.payments.enums.PaymentStatus;
import br.com.brunood.payments.enums.PaymentType;
import br.com.brunood.payments.repositories.PaymentsRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CreatePaymentUseCase {

    @Autowired
    private PaymentsRepository paymentsRepository;

    @RabbitListener(queues = "payment.created")
    public void execute(CreatePaymentDTO data) {

        var paymentStatus = data.getPaymentType().equals(PaymentType.CREDIT_CARD.getValue()) ? PaymentStatus.PROCESSING.getValue() : PaymentStatus.PENDING.getValue();

        var payment = Payments.builder()
                .clientDocument(data.getClientDocument())
                .installments(data.getInstallments())
                .orderId(data.getOrderId())
                .paymentStatus(paymentStatus)
                .paymentType(data.getPaymentType())
                .value(data.getValue())
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        this.paymentsRepository.save(payment);
    }

}
