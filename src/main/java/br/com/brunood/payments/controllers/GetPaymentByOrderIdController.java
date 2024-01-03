package br.com.brunood.payments.controllers;

import br.com.brunood.payments.usecases.GetPaymentByOrderIdUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
public class GetPaymentByOrderIdController {

    @Autowired
    private GetPaymentByOrderIdUseCase getPaymentByOrderIdUseCase;

    @GetMapping("/{orderId}")
    public ResponseEntity<Object> getPaymentByOrderId(@PathVariable(name = "orderId") Long orderId) {
        try {
            var payment = this.getPaymentByOrderIdUseCase.execute(orderId);

            return ResponseEntity.ok().body(payment);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
