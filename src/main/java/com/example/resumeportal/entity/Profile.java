package com.example.resumeportal.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "profiles")
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "applicant_id")
    private User applicant;

    private String resumeFilePath;
    @Column(columnDefinition = "TEXT")
    private String skills;
    @Column(columnDefinition = "TEXT")
    private String education;
    @Column(columnDefinition = "TEXT")
    private String experience;
    private String name;
    private String email;
    private String phone;

public Profile(){

}

    public Profile(Long id, User applicant, String resumeFilePath, String skills, String education, String experience, String name, String email, String phone) {
        this.id = id;
        this.applicant = applicant;
        this.resumeFilePath = resumeFilePath;
        this.skills = skills;
        this.education = education;
        this.experience = experience;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getApplicant() {
        return applicant;
    }

    public void setApplicant(User applicant) {
        this.applicant = applicant;
    }

    public String getResumeFilePath() {
        return resumeFilePath;
    }

    public void setResumeFilePath(String resumeFilePath) {
        this.resumeFilePath = resumeFilePath;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
