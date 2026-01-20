package tn.enis.fooddelivery.delivery.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tn.enis.fooddelivery.delivery.dto.UpdateStatusRequest;
import tn.enis.fooddelivery.delivery.model.Delivery;
import tn.enis.fooddelivery.delivery.service.DeliveryService;
import tn.enis.fooddelivery.delivery.service.GpsService;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;
    private final GpsService gpsService;

    public DeliveryController(DeliveryService deliveryService, GpsService gpsService) {
        this.deliveryService = deliveryService;
        this.gpsService = gpsService;
    }

    // Get all deliveries
    @GetMapping
    public ResponseEntity<List<Delivery>> getAllDeliveries() {
        return ResponseEntity.ok(deliveryService.getAllDeliveries());
    }

    // See available deliveries
    @GetMapping("/available")
    public ResponseEntity<List<Delivery>> getAvailableDeliveries() {
        return ResponseEntity.ok(deliveryService.getAvailableDeliveries());
    }

    // Accept a delivery
    @PutMapping("/{id}/accept")
    public ResponseEntity<Delivery> acceptDelivery(
            @PathVariable Long id,
            @RequestParam Long livreurId) {

        return ResponseEntity.ok(deliveryService.acceptDelivery(id, livreurId));
    }

    // Update delivery status
    @PutMapping("/{id}/status")
    public ResponseEntity<Delivery> updateStatus(
            @PathVariable Long id,
            @RequestBody UpdateStatusRequest request) {

        return ResponseEntity.ok(deliveryService.updateStatus(id, request.getStatus()));
    }

    // Get delivery route (mock GPS)
    @GetMapping("/{id}/route")
    public ResponseEntity<Map<String, String>> getRoute(@PathVariable Long id) {
        return ResponseEntity.ok(
                gpsService.getRoute("restaurant", "client")
        );
    }

    // Create delivery
    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery) {
        return ResponseEntity.ok(deliveryService.createDelivery(delivery));
    }
}
