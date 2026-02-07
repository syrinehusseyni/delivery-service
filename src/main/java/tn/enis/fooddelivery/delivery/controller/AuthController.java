package tn.enis.fooddelivery.delivery.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.enis.fooddelivery.delivery.dto.ErrorResponse;
import tn.enis.fooddelivery.delivery.dto.LoginRequest;
import tn.enis.fooddelivery.delivery.dto.LoginResponse;
import tn.enis.fooddelivery.delivery.model.Deliverer;
import tn.enis.fooddelivery.delivery.repository.DelivererRepository;
import tn.enis.fooddelivery.delivery.util.JwtTokenProvider;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final DelivererRepository delivererRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final BCryptPasswordEncoder passwordEncoder;

    public AuthController(DelivererRepository delivererRepository,
                          JwtTokenProvider jwtTokenProvider,
                          BCryptPasswordEncoder passwordEncoder) {
        this.delivererRepository = delivererRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {

        Optional<Deliverer> optionalDeliverer = delivererRepository.findByEmail(request.getEmail());
        if (optionalDeliverer.isEmpty()) {
            return ResponseEntity.status(401)
                    .body(new ErrorResponse("Invalid credentials", 401));
        }

        Deliverer deliverer = optionalDeliverer.get();

        // Compare the plain password with the hashed password
        if (!passwordEncoder.matches(request.getPassword(), deliverer.getPassword())) {
            return ResponseEntity.status(401)
                    .body(new ErrorResponse("Invalid credentials", 401));
        }

        // Generate JWT token
        String token = jwtTokenProvider.generateToken(deliverer.getEmail(), deliverer.getRole());

        return ResponseEntity.ok(new LoginResponse(
                token,
                deliverer.getEmail(),
                deliverer.getRole()
        ));
    }
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Deliverer deliverer) {
        // Check if email already exists
        if (delivererRepository.findByEmail(deliverer.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(new ErrorResponse("Email already in use", 400));
        }

        // Hash the password automatically
        deliverer.setPassword(passwordEncoder.encode(deliverer.getPassword()));
        // optional if not set by default

        Deliverer savedDeliverer = delivererRepository.save(deliverer);

        return ResponseEntity.ok(savedDeliverer);
    }

}
