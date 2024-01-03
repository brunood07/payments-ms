package br.com.brunood.payments.usecases;

import br.com.brunood.payments.enums.PaymentStatus;
import br.com.brunood.payments.exceptions.PaymentNotFoundException;
import br.com.brunood.payments.repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class UpdatePaymentStatusUseCase {

    @Autowired
    private PaymentsRepository paymentsRepository;

    public void execute(Long paymentId, PaymentStatus paymentStatus) {
        var payment = this.paymentsRepository.findById(paymentId).orElseThrow(PaymentNotFoundException::new);

        payment.setUpdatedAt(LocalDateTime.now());
        payment.setPaymentStatus(paymentStatus.getValue());

        this.paymentsRepository.save(payment);
    }
}
