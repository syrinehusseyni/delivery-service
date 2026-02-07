package tn.enis.fooddelivery.delivery.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import tn.enis.fooddelivery.delivery.dto.UpdateStatusRequest;
import tn.enis.fooddelivery.delivery.model.Delivery;
import tn.enis.fooddelivery.delivery.service.DeliveryService;

@RestController
@RequestMapping("/api/delivery")  // ← CORRIGÉ: /api/deliveries → /api/delivery
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    // ✅ NOUVEAU: Test d'authentification
    @GetMapping("/test-auth")
    public ResponseEntity<String> testAuth() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            return ResponseEntity.ok("✅ Authentifié en tant que: " + auth.getName());
        }
        return ResponseEntity.status(401).body("❌ Non authentifié");
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

    @PutMapping("/{id}/status")
    public ResponseEntity<Delivery> updateStatus(
            @PathVariable Long id,
            @Valid @RequestBody UpdateStatusRequest request) {

        return ResponseEntity.ok(deliveryService.updateStatus(id, request.getStatus()));
    }


    @PostMapping
    public ResponseEntity<Delivery> createDelivery(@RequestBody Delivery delivery) {
        return ResponseEntity.ok(deliveryService.createDelivery(delivery));
    }
}