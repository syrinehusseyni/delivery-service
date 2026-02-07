package tn.enis.fooddelivery.delivery.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;

public class LoginResponse {
    @NotBlank(message = "Token is required")
    private String token;

    @NotBlank(message = "Username is required")
    private String username;

    private String role;
    private LocalDateTime issuedAt;

    public LoginResponse() {
        this.issuedAt = LocalDateTime.now();
    }

    public LoginResponse(String token, String username, String role) {
        this.token = token;
        this.username = username;
        this.role = role;
        this.issuedAt = LocalDateTime.now();
    }

    // Getters and setters
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public LocalDateTime getIssuedAt() {
        return issuedAt;
    }

    public void setIssuedAt(LocalDateTime issuedAt) {
        this.issuedAt = issuedAt;
    }
}
