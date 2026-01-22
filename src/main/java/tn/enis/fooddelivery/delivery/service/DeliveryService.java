package tn.enis.fooddelivery.delivery.service;

import java.util.List;

import org.springframework.stereotype.Service;

import tn.enis.fooddelivery.delivery.model.Delivery;
import tn.enis.fooddelivery.delivery.model.DeliveryStatus;
import tn.enis.fooddelivery.delivery.repository.DeliveryRepository;

@Service
public class DeliveryService {

    private final DeliveryRepository repository;

    public DeliveryService(DeliveryRepository repository) {
        this.repository = repository;
    }

    // Get all available deliveries
    public List<Delivery> getAvailableDeliveries() {
        return repository.findByStatus(DeliveryStatus.AVAILABLE);
    }
     public List<Delivery> getAllDeliveries() {
        return repository.findAll();
    }
    // Accept a delivery
    public Delivery acceptDelivery(Long id, Long livreurId) {
        Delivery delivery = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        delivery.setLivreurId(livreurId);
        delivery.setStatus(DeliveryStatus.ASSIGNED);

        return repository.save(delivery);
    }

    // Update delivery status
    public Delivery updateStatus(Long id, DeliveryStatus status) {
        Delivery delivery = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));

        delivery.setStatus(status);
        return repository.save(delivery);
    }

    // For admin/testing: create a delivery
    public Delivery createDelivery(Delivery delivery) {
        return repository.save(delivery);
    }
}
