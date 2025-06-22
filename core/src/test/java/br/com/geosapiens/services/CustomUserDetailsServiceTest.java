package br.com.geosapiens.services;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class CustomUserDetailsServiceTest {

    private final CustomUserDetailsService service = new CustomUserDetailsService();

    @Test
    void shouldReturnUserDetailsForKnownUser() {
        UserDetails details = service.loadUserByUsername("user_teste");
        assertEquals("user_teste", details.getUsername());
    }

    @Test
    void shouldThrowWhenUserNotFound() {
        assertThrows(UsernameNotFoundException.class, () -> service.loadUserByUsername("unknown"));
    }
}
