package com.ThallesDaniel.criptopay.service;

import com.ThallesDaniel.criptopay.entity.User;
import com.ThallesDaniel.criptopay.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setEmail("test@example.com");
        user.setPassword("123456");
        user.setName("Test User");
    }

    @Test
    void registerUser_shouldSaveUser_whenEmailDoesNotExist() throws Exception {
        // Simula que o email não existe
        when(userRepository.findByEmail(user.getEmail())).thenReturn(null);
        when(passwordEncoder.encode(user.getPassword())).thenReturn("encodedPassword");
        when(userRepository.save(any(User.class))).thenAnswer(i -> i.getArguments()[0]);

        User savedUser = userService.registerUser(user);

        assertNotNull(savedUser);
        assertEquals("encodedPassword", savedUser.getPassword());
        assertFalse(savedUser.isEnabled());
        assertNotNull(savedUser.getVerificationCode());

        verify(userRepository, times(1)).save(user);
    }

    @Test
    void registerUser_shouldThrowException_whenEmailExists() {
        // Simula que o email já existe
        when(userRepository.findByEmail(user.getEmail())).thenReturn(new User());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            userService.registerUser(user);
        });

        assertEquals("This email already exists", exception.getMessage());
        verify(userRepository, never()).save(any(User.class));
    }
}
