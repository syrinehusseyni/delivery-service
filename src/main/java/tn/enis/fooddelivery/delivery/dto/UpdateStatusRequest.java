package tn.enis.fooddelivery.delivery.dto;

import tn.enis.fooddelivery.delivery.model.DeliveryStatus;

public class UpdateStatusRequest {
    private DeliveryStatus status;

    public DeliveryStatus getStatus() {
        return status;
    }

    public void setStatus(DeliveryStatus status) {
        this.status = status;
    }
}
