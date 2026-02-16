package tn.enis.fooddelivery.delivery.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class LoginResponse {
    @NotBlank(message = "Token is required")
    private String token;

    @NotBlank(message = "Username is required")
    private String email;


    public LoginResponse(String token, String username) {
        this.token = token;
        this.email = username;
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setEmail(String em) {
        this.email = em;
    }

    public String getEmail() {
        return email;
    }

}
