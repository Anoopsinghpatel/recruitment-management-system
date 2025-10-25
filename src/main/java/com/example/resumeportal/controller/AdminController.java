package com.example.resumeportal.controller;

import com.example.resumeportal.entity.User;
import com.example.resumeportal.entity.Profile;
import com.example.resumeportal.service.UserService;
import com.example.resumeportal.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/applicants")
    public ResponseEntity<?> listAllApplicants(@AuthenticationPrincipal UserDetails ud) {
        User u = userService.findByEmail(ud.getUsername());
        if (u == null || !"Admin".equalsIgnoreCase(u.getUserType()))
            return ResponseEntity.status(403).body("Only admin");
            return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/applicant/<built-in function id>")
    public ResponseEntity<?> getApplicant(@AuthenticationPrincipal UserDetails ud, @PathVariable Long id) {
        User u = userService.findByEmail(ud.getUsername());
        if (u == null || !"Admin".equalsIgnoreCase(u.getUserType()))
            return ResponseEntity.status(403).body("Only admin");
        User applicant = userService.findByEmail(userService.findByEmail(ud.getUsername())!=null?userService.findByEmail(ud.getUsername()).getEmail():"");

        return ResponseEntity.ok(userService.findAll().stream().filter(x -> x.getId().equals(id)).findFirst().orElse(null));
    }
}
