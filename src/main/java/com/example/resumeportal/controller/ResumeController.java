package com.example.resumeportal.controller;

import com.example.resumeportal.entity.User;
import com.example.resumeportal.entity.Profile;
import com.example.resumeportal.service.ResumeService;
import com.example.resumeportal.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ResumeController {
    private final ResumeService resumeService;
    private final UserService userService;

    public ResumeController(ResumeService resumeService, UserService userService) {
        this.resumeService = resumeService;
        this.userService = userService;
    }

    @PostMapping("/uploadResume")
    @PreAuthorize("hasAuthority('Applicant')")
    public ResponseEntity<?> uploadResume(@AuthenticationPrincipal UserDetails ud,
                                          @RequestPart("file") MultipartFile file) throws Exception {
        User user = userService.findByEmail(ud.getUsername());
        Profile p = resumeService.uploadAndParse(user, file);
        return ResponseEntity.ok(p);
    }


}
