package com.example.backend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "http://localhost:3000")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/login")
    public String login(@RequestBody Admin admin) {
        Admin existingAdmin = adminService.findByAdminId(admin.getAdminId());
        if (existingAdmin == null) {
            return "Admin not found";
        }
        if (!existingAdmin.getPassword().equals(admin.getPassword())) {
            return "Invalid password";
        }
        return "Login successful";
    }
}