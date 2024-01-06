package br.com.brunood.payments.usecases;

import br.com.brunood.payments.factories.PaymentFactoryTest;
import br.com.brunood.payments.repositories.PaymentsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CreatePaymentUseCaseTest {

    @Mock
    PaymentsRepository paymentsRepository;
    @InjectMocks
    private CreatePaymentUseCase createPaymentUseCase;

    @Test
    void shouldBeAbleToCreatePaymentWithPixAndInvoiceValidPayload() {
        when(paymentsRepository.save(any())).thenReturn(PaymentFactoryTest.payment());

        createPaymentUseCase.execute(PaymentFactoryTest.createPaymentPayload());

        verify(paymentsRepository, times(1)).save(any());
    }

    @Test
    void shouldBeAbleToCreatePaymentWithCreditCardValidPayload() {
        when(paymentsRepository.save(any())).thenReturn(PaymentFactoryTest.payment());

        createPaymentUseCase.execute(PaymentFactoryTest.createPaymentPayloadWithCard());

        verify(paymentsRepository, times(1)).save(any());
    }

}
