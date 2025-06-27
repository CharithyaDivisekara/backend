package com.example.backend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/signup")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping
    public String signup(@RequestBody User user) {
        if (userService.emailExists(user.getEmail())) {
            return "Email already exists";
        }
        userService.saveUser(user);
        return "Signup successful";
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
    User existingUser = userService.findByEmail(user.getEmail());
    if (existingUser == null) {
        return "User not found";
    }
    if (!existingUser.getPassword().equals(user.getPassword())) {
        return "Invalid password";
    }
    return "Login successful";
    }
}    