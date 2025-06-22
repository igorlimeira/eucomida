package br.com.geosapiens.auth.controllers;

import br.com.geosapiens.auth.interfaces.AuthServiceInterface;
import br.com.geosapiens.auth.models.AuthRequest;
import br.com.geosapiens.auth.models.AuthResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private AuthServiceInterface authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request) {
        return ResponseEntity.ok(this.authService.auth(request));
    }
}
