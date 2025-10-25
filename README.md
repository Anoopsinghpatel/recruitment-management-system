# 🧾 Resume Portal

A backend application built with **Spring Boot**, **JWT Authentication**, and **MySQL**, where:
- Applicants can register, upload and parse resumes automatically using an external Resume Parser API.
- Admins can post job openings and manage applicants.
- Authentication is secured with JSON Web Tokens (JWT).

---

## 🚀 Features

### 👤 User Module
- Signup & Login with JWT authentication
- Role-based access: **Applicant** and **Admin**
- Applicants can upload resumes and view parsed profiles

### 📄 Resume Parsing
- Upload resumes in PDF/DOCX format
- Integrated with [Apilayer Resume Parser API](https://apilayer.com)
- Extracts details like name, email, phone, skills, education, and experience

### 💼 Job Management
- Admins can create and manage job posts
- Applicants can view and apply for available jobs

---

## 🛠️ Tech Stack

**Backend:** Spring Boot, Hibernate, MySQL  
**Security:** Spring Security + JWT  
**External API:** Apilayer Resume Parser  
**Build Tool:** Maven  

---


