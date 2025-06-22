package br.com.geosapiens.utils;

import br.com.geosapiens.exceptions.models.TokenCreationException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class JwtUtilsTest {

    private JwtUtils jwtUtils;

    @BeforeEach
    void setUp() throws Exception {
        jwtUtils = new JwtUtils();
        setField("jwtSecret", "secret-test");
        setField("jwtExpirationMs", 3600L);
        setField("issuer", "testIssuer");
    }

    private void setField(String name, Object value) throws Exception {
        Field field = JwtUtils.class.getDeclaredField(name);
        field.setAccessible(true);
        field.set(jwtUtils, value);
    }

    @Test
    void shouldGenerateAndValidateToken() {
        String token = jwtUtils.generateToken("user");
        assertTrue(jwtUtils.validateJwtToken(token));
        assertEquals("user", jwtUtils.getUsernameFromJwt(token));
    }

    @Test
    void shouldReturnFalseForInvalidToken() {
        assertFalse(jwtUtils.validateJwtToken("invalid.token"));
    }

    @Test
    void shouldThrowExceptionWhenTokenIsInvalidOnExtraction() {
        assertThrows(TokenCreationException.class, () -> jwtUtils.getUsernameFromJwt("invalid.token"));
    }
}
