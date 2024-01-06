package br.com.brunood.payments.usecases;

import br.com.brunood.payments.enums.PaymentStatus;
import br.com.brunood.payments.exceptions.PaymentNotFoundException;
import br.com.brunood.payments.factories.PaymentFactoryTest;
import br.com.brunood.payments.repositories.PaymentsRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@SpringBootTest
public class UpdatePaymentStatusUseCaseTest {

    @Mock
    PaymentsRepository paymentsRepository;
    @InjectMocks
    UpdatePaymentStatusUseCase updatePaymentStatusUseCase;

    @Test
    void shouldBeAbleToUpdateAExistingOrderStatus() {
        when(paymentsRepository.findById(anyLong())).thenReturn(Optional.of(PaymentFactoryTest.payment()));

        updatePaymentStatusUseCase.execute(1L, PaymentStatus.SETTLED);

        verify(paymentsRepository, times(1)).findById(anyLong());
    }

    @Test
    void shouldThrowExceptionIfOrderDontExists() {
        when(paymentsRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> updatePaymentStatusUseCase.execute(1L, PaymentStatus.SETTLED));

        verify(paymentsRepository, times(1)).findById(anyLong());
    }
}
