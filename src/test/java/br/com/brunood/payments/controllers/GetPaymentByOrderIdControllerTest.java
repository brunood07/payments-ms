package br.com.brunood.payments.controllers;

import br.com.brunood.payments.entities.Payments;
import br.com.brunood.payments.exceptions.PaymentNotFoundException;
import br.com.brunood.payments.factories.PaymentFactoryTest;
import br.com.brunood.payments.repositories.PaymentsRepository;
import br.com.brunood.payments.usecases.GetPaymentByOrderIdUseCase;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GetPaymentByOrderIdControllerTest {

    @Mock
    GetPaymentByOrderIdUseCase getPaymentByOrderIdUseCase;
    @Mock
    PaymentsRepository paymentsRepository;
    @InjectMocks
    GetPaymentByOrderIdController getPaymentByOrderIdController;
    private final String baseUrl = "/api/v1/payments";

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.standaloneSetup(getPaymentByOrderIdController);
    }

    @Test
    void shouldReturn404WithAMalformedUrl() {
        String malformedUrl = "/api/v1/payment";

        given().when()
                .put(malformedUrl)
                .then()
                .statusCode(404);
    }

    @Test
    void shouldReturn200WhenGetPaymentByOrderIdWithValidPayload() {
        when(getPaymentByOrderIdUseCase.execute(any())).thenReturn(PaymentFactoryTest.payment());

        given().when()
                .get(baseUrl + "/1")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturn400WhenGetPaymentByOrderIdWithInvalidPayload() {
        when(paymentsRepository.findByOrderId(anyLong())).thenReturn(Optional.empty());
        when(getPaymentByOrderIdUseCase.execute(anyLong())).thenThrow(PaymentNotFoundException.class);

        given().when()
                .get(baseUrl + "/1")
                .then()
                .statusCode(400);
    }
}
