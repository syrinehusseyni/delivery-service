package tn.enis.fooddelivery.delivery.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import tn.enis.fooddelivery.delivery.dto.UserDTO;

@FeignClient(name = "user-service") // Name of the User Service in Eureka
public interface UserClient {

    @GetMapping("/users/{id}")
    UserDTO getUserById(@PathVariable("id") Long userId);
}
