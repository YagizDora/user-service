package com.example.user_service.service;

import com.example.user_service.model.User;
import com.example.user_service.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    // Create
    public User createUser(User user) {
        return repository.save(user);
    }

    // Read All
    public List<User> getAllUsers() {
        return repository.findAll();
    }

    // Read One
    public User getUserById(String id) {
        return repository.findById(id).orElse(null);
    }

    // Update
    public User updateUser(String id, User userDetails) {
        User user = repository.findById(id).orElse(null);
        if (user != null) {
            user.setName(userDetails.getName());
            user.setSurname(userDetails.getSurname());
            user.setEmail(userDetails.getEmail());
            user.setPhoneNumber(userDetails.getPhoneNumber());
            user.setAge(userDetails.getAge());
            user.setGovId(userDetails.getGovId());
            return repository.save(user);
        }
        return null;
    }

    // Delete
    public void deleteUser(String id) {
        repository.deleteById(id);
    }

    // --- INITIAL DATA LOADER (So you don't have empty arrays) ---
    @PostConstruct
    public void initData() {
        if (repository.count() == 0) {
            System.out.println(">>> GENERATING INITIAL USERS...");

            User u1 = new User();
            u1.setName("John");
            u1.setSurname("Doe");
            u1.setEmail("john@example.com");
            u1.setPhoneNumber("555-0199");
            u1.setAge(30);
            u1.setGovId("12345678901");
            repository.save(u1);

            User u2 = new User();
            u2.setName("Jane");
            u2.setSurname("Smith");
            u2.setEmail("jane@example.com");
            u2.setPhoneNumber("555-0200");
            u2.setAge(25);
            u2.setGovId("98765432109");
            repository.save(u2);
        }
    }
}