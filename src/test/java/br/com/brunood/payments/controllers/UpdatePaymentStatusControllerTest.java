package br.com.brunood.payments.controllers;

import br.com.brunood.payments.enums.PaymentStatus;
import br.com.brunood.payments.exceptions.PaymentNotFoundException;
import br.com.brunood.payments.factories.PaymentFactoryTest;
import br.com.brunood.payments.repositories.PaymentsRepository;
import br.com.brunood.payments.usecases.GetPaymentByOrderIdUseCase;
import br.com.brunood.payments.usecases.UpdatePaymentStatusUseCase;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UpdatePaymentStatusControllerTest {

    @Mock
    UpdatePaymentStatusUseCase updatePaymentStatusUseCase;
    @Mock
    PaymentsRepository paymentsRepository;
    @InjectMocks
    UpdatePaymentStatusController updatePaymentStatusController;
    private final String baseUrl = "/api/v1/payments";

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.standaloneSetup(updatePaymentStatusController);
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
    void shouldReturn200WhenUpdatePaymentByOrderIdWithValidPayload() {
        given().when()
                .put(baseUrl + "/update/1?status=PROCESSING")
                .then()
                .statusCode(200);
    }

    @Test
    void shouldReturn400WhenUpdatePaymentByOrderIdWithInvalidPayload() {
        long orderId = 1L;
        when(paymentsRepository.findById(orderId)).thenReturn(Optional.empty());
        doThrow(PaymentNotFoundException.class).when(updatePaymentStatusUseCase).execute(anyLong(), any());

        given()
                .contentType("application/json")
                .body("invalid_payload")
                .when()
                .put(baseUrl + "/update/1?status=PROCESSING")
                .then()
                .statusCode(400);
    }
}
