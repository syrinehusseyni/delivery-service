package tn.enis.fooddelivery.delivery.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tn.enis.fooddelivery.delivery.dto.OrderDTO;

@FeignClient(name = "order-service") // Name of the Order Service in Eureka
public interface OrderClient {

    @GetMapping("/orders/{id}")
    OrderDTO getOrderById(@PathVariable("id") Long orderId);
}
