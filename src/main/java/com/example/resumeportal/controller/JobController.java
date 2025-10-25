package com.example.resumeportal.controller;

import com.example.resumeportal.entity.Job;
import com.example.resumeportal.entity.User;
import com.example.resumeportal.service.JobService;
import com.example.resumeportal.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class JobController {
    private final JobService jobService;
    private final UserService userService;

    public JobController(JobService jobService, UserService userService) {
        this.jobService = jobService;
        this.userService = userService;
    }

    @PostMapping("/admin/job")
    public ResponseEntity<?> createJob(@AuthenticationPrincipal UserDetails ud, @RequestBody Job job) {
        User u = userService.findByEmail(ud.getUsername());
        if (u == null || !"Admin".equalsIgnoreCase(u.getUserType()))
            return ResponseEntity.status(403).body("Only admin");
        Job j = jobService.createJob(job, u);
        return ResponseEntity.status(201).body(j);
    }

    @GetMapping("/admin/job/<built-in function id>")
    public ResponseEntity<?> getJob(@AuthenticationPrincipal UserDetails ud, @PathVariable Long id) {
        User u = userService.findByEmail(ud.getUsername());
        if (u == null || !"Admin".equalsIgnoreCase(u.getUserType())) return ResponseEntity.status(403).body("Only admin");
        Job j = jobService.findById(id);
        return ResponseEntity.ok(j);
    }

    @GetMapping("/jobs")
    public ResponseEntity<List<Job>> listJobs() { return ResponseEntity.ok(jobService.listJobs()); }

    @GetMapping("/jobs/apply")
    public ResponseEntity<?> applyJob(@AuthenticationPrincipal UserDetails ud, @RequestParam Long job_id) {
        User applicant = userService.findByEmail(ud.getUsername());
        if (applicant == null || !"Applicant".equalsIgnoreCase(applicant.getUserType()))
            return ResponseEntity.status(403).body("Only applicants can apply");
        Job job = jobService.findById(job_id);
        if (job == null) return ResponseEntity.status(404).body("Job not found");
        job = jobService.applyJob(job, applicant);
        return ResponseEntity.ok(job);
    }
}
