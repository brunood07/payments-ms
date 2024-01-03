package br.com.brunood.payments.usecases;

import br.com.brunood.payments.entities.Payments;
import br.com.brunood.payments.exceptions.PaymentNotFoundException;
import br.com.brunood.payments.repositories.PaymentsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GetPaymentByOrderIdUseCase {

    @Autowired
    PaymentsRepository paymentsRepository;

    public Payments execute(Long orderId) {

        return this.paymentsRepository.findByOrderId(orderId).orElseThrow(PaymentNotFoundException::new);
    }
}
