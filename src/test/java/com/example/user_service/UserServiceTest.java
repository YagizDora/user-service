package com.example.user_service;

import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import com.example.user_service.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;

    @Test
    public void testCreateUser() {
        // 1. Setup Data
        User user = new User();
        user.setName("Test");
        user.setSurname("User");
        user.setGovId("11111111111");

        // 2. Mock the Database behavior (When I save, return the user)
        when(repository.save(any(User.class))).thenReturn(user);

        // 3. Call the Service
        User created = service.createUser(user);

        // 4. Assertions (Check if it worked)
        assertNotNull(created);
        assertEquals("Test", created.getName());
        assertEquals("11111111111", created.getGovId());
    }

    @Test
    public void testGetUserById() {
        // 1. Setup Data
        User user = new User();
        user.setId("user-123");
        user.setName("Alice");

        // 2. Mock Database
        when(repository.findById("user-123")).thenReturn(Optional.of(user));

        // 3. Call Service
        User found = service.getUserById("user-123");

        // 4. Assertions
        assertEquals("Alice", found.getName());
    }
}