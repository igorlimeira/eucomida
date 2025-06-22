package br.com.geosapiens.auth.interfaces;

import br.com.geosapiens.auth.models.AuthRequest;
import br.com.geosapiens.auth.models.AuthResponse;
import org.springframework.security.core.AuthenticationException;

public interface AuthServiceInterface {
    /**
     * Authenticates a user based on the provided credentials.
     *
     * @param request The authentication request containing user credentials.
     * @return A string representing the access token if authentication is successful.
     * @throws AuthenticationException If authentication fails due to invalid credentials or other issues.
     */
    AuthResponse auth(AuthRequest request) throws AuthenticationException;
}
