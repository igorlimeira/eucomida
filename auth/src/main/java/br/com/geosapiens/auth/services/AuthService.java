package br.com.geosapiens.auth.services;

import br.com.geosapiens.auth.interfaces.AuthServiceInterface;
import br.com.geosapiens.auth.models.AuthRequest;
import br.com.geosapiens.auth.models.AuthResponse;
import br.com.geosapiens.utils.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService implements AuthServiceInterface {

    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;

    @Override
    public AuthResponse auth(AuthRequest request) throws AuthenticationException {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.username(), request.password()));

        if (auth.isAuthenticated()) {
            String jwt = jwtUtils.generateToken(request.username());
            return new AuthResponse(jwt);
        } else {
            throw new RuntimeException("Authentication failed");
        }
    }

}
