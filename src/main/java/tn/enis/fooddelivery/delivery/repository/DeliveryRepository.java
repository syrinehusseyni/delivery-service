package tn.enis.fooddelivery.delivery.repository;

import tn.enis.fooddelivery.delivery.model.Delivery;
import tn.enis.fooddelivery.delivery.model.DeliveryStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {
    List<Delivery> findByStatus(DeliveryStatus status);
}
