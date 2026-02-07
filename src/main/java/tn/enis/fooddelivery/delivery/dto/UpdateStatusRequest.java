package tn.enis.fooddelivery.delivery.dto;

import jakarta.validation.constraints.NotNull;
import tn.enis.fooddelivery.delivery.model.DeliveryStatus;

public class UpdateStatusRequest {
    @NotNull(message = "Status is required")
    private DeliveryStatus status;

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
