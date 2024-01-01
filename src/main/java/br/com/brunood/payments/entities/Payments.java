package br.com.brunood.payments.entities;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "tb_payments")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_id")
    private Long paymentId;

    @Column(name = "order_id")
    private Long orderId;

    private BigDecimal value;

    @Column(name = "payment_type")
    private String paymentType;

    @Column(name = "payment_status")
    private String paymentStatus;

    private String description;

    private int installments;

    @Column(name = "client_document")
    private String clientDocument;
}
