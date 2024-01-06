package br.com.brunood.payments.usecases;

import br.com.brunood.payments.exceptions.PaymentNotFoundException;
import br.com.brunood.payments.factories.PaymentFactoryTest;
import br.com.brunood.payments.repositories.PaymentsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class GetPaymentByOrderIdUseCaseTest {

    @Mock
    PaymentsRepository paymentsRepository;
    @InjectMocks
    GetPaymentByOrderIdUseCase getPaymentByOrderIdUseCase;

    @Test
    void shouldBeAbleToReturnAPaymentIfExists() {
        when(paymentsRepository.findByOrderId(anyLong())).thenReturn(Optional.of(PaymentFactoryTest.payment()));

        var payment = getPaymentByOrderIdUseCase.execute(1L);

        verify(paymentsRepository, times(1)).findByOrderId(anyLong());
        assertEquals("11111111111", payment.getClientDocument());
    }

    @Test
    void shouldThrowExceptionIfPaymentNotFound() {
        when(paymentsRepository.findByOrderId(anyLong())).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> getPaymentByOrderIdUseCase.execute(1L));

        verify(paymentsRepository, times(1)).findByOrderId(anyLong());
    }

}
