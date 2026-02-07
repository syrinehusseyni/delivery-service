package tn.enis.fooddelivery.delivery.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.enis.fooddelivery.delivery.model.Deliverer;

import java.util.Optional;

public interface DelivererRepository extends JpaRepository<Deliverer, Long> {
    Optional<Deliverer> findByEmail(String email);
}
