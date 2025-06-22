package br.com.geosapiens.utils;

import br.com.geosapiens.exceptions.models.TokenCreationException;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Date;


@Component
public class JwtUtils {

    @Value("${eucomida.config.security.jwt.secret}")
    private String jwtSecret;

    @Value("${eucomida.config.security.jwt.expiration:86400000}") // Default to 24 hours if not set
    private long jwtExpirationMs;

    @Value("${eucomida.config.security.issuer:euComida}")
    private String issuer;


    public String generateToken(String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.create()
                    .withIssuer(issuer)
                    .withSubject(username)
                    .withIssuedAt(new Date())
                    .withExpiresAt(Date.from(Instant.now().plusSeconds(jwtExpirationMs)))
                    .sign(algorithm);
        } catch (JWTCreationException ex) {
            throw new TokenCreationException("Error generating JWT", ex);
        }
    }

    public boolean validateJwtToken(String jwt) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(jwt);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String getUsernameFromJwt(String jwt) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(jwtSecret);
            return JWT.require(algorithm)
                    .withIssuer(issuer)
                    .build()
                    .verify(jwt)
                    .getSubject();
        } catch (Exception e) {
            throw new TokenCreationException("Error extracting username from JWT", e);
        }
    }
}
