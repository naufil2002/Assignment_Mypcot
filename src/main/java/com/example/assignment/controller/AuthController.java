package com.example.assignment.controller;

import com.example.assignment.User;
import com.example.assignment.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {
		  "http://localhost:3000",
		  "https://assignment-mypcot-frontend.vercel.app"
		})
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepo;

    // Register
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userRepo.save(user);
    }

    // Login with error handling
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User found = userRepo.findByEmail(user.getEmail());

        if (found == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User not found with this email");
        }

        if (!found.getPassword().equals(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Incorrect password");
        }

        return ResponseEntity.ok(found);
    }
}
