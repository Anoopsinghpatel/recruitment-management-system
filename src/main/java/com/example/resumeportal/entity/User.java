package com.example.resumeportal.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Column(unique = true)
    private String email;
    private String address;
    private String userType;
    private String passwordHash;
    private String profileHeadline;

    @OneToOne(mappedBy = "applicant", cascade = CascadeType.ALL, orphanRemoval = true)
    private Profile profile;

    public User(){

    }

    public User(Long id, String name, String email, String address, String userType, String passwordHash, String profileHeadline, Profile profile) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.address = address;
        this.userType = userType;
        this.passwordHash = passwordHash;
        this.profileHeadline = profileHeadline;
        this.profile = profile;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getProfileHeadline() {
        return profileHeadline;
    }

    public void setProfileHeadline(String profileHeadline) {
        this.profileHeadline = profileHeadline;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}



