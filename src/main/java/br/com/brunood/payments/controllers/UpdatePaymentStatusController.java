package br.com.brunood.payments.controllers;

import br.com.brunood.payments.enums.PaymentStatus;
import br.com.brunood.payments.usecases.UpdatePaymentStatusUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/payments")
public class UpdatePaymentStatusController {

    @Autowired
    private UpdatePaymentStatusUseCase updatePaymentStatusUseCase;

    @PutMapping("/update/{paymentId}")
    public ResponseEntity<Object> updatePaymentStatus(@PathVariable(name = "paymentId") Long paymentId, @RequestParam("status")PaymentStatus status) {
        try {
            this.updatePaymentStatusUseCase.execute(paymentId, status);

            return ResponseEntity.ok().body("request accepted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
