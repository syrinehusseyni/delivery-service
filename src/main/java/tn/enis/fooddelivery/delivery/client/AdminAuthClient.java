package tn.enis.fooddelivery.delivery.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tn.enis.fooddelivery.delivery.dto.LoginRequest;
import tn.enis.fooddelivery.delivery.dto.LoginResponse;

@FeignClient(name = "admin-service", url = "http://localhost:8081")
public interface AdminAuthClient {

    @PostMapping("/api/admin/auth/login")
    LoginResponse login(@RequestBody LoginRequest loginRequest);

    class LoginRequest {
        private String email;
        private String password;

        // constructors, getters, setters
    }

    class LoginResponse {
        private String token;
        private String email;
        // getters, setters
    }
}
