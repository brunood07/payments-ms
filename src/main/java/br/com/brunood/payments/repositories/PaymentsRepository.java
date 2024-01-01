package br.com.brunood.payments.repositories;

import br.com.brunood.payments.entities.Payments;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentsRepository extends JpaRepository<Payments, Long> {
}
