package com.example.resumeportal.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "jobs")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    @Column(columnDefinition = "TEXT")
    private String description;
    private LocalDateTime postedOn;
    private int totalApplications;
    private String companyName;

    @ManyToOne
    @JoinColumn(name = "posted_by")
    private User postedBy;

    @ManyToMany
    @JoinTable(name = "job_applications",
      joinColumns = @JoinColumn(name = "job_id"),
      inverseJoinColumns = @JoinColumn(name = "applicant_id"))
    private Set<User> applicants = new HashSet<>();

    public Job(){

    }

    public Job(Long id, String title, String description, LocalDateTime postedOn, int totalApplications, String companyName, User postedBy, Set<User> applicants) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.postedOn = postedOn;
        this.totalApplications = totalApplications;
        this.companyName = companyName;
        this.postedBy = postedBy;
        this.applicants = applicants;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getPostedOn() {
        return postedOn;
    }

    public void setPostedOn(LocalDateTime postedOn) {
        this.postedOn = postedOn;
    }

    public int getTotalApplications() {
        return totalApplications;
    }

    public void setTotalApplications(int totalApplications) {
        this.totalApplications = totalApplications;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public User getPostedBy() {
        return postedBy;
    }

    public void setPostedBy(User postedBy) {
        this.postedBy = postedBy;
    }

    public Set<User> getApplicants() {
        return applicants;
    }

    public void setApplicants(Set<User> applicants) {
        this.applicants = applicants;
    }
}
