package com.example.resumeportal.service;

import com.example.resumeportal.entity.Job;
import com.example.resumeportal.entity.User;
import com.example.resumeportal.repository.JobRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class JobService {
    private final JobRepository jobRepository;

    public JobService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job createJob(Job job, User postedBy) {
        job.setPostedOn(LocalDateTime.now());
        job.setPostedBy(postedBy);
        job.setTotalApplications(0);
        return jobRepository.save(job);
    }

    public List<Job> listJobs() {
        return jobRepository.findAll();
    }

    public Job findById(Long id) {
        return jobRepository.findById(id).orElse(null);
    }

    public Job applyJob(Job job, User applicant) {
        job.getApplicants().add(applicant);
        job.setTotalApplications(job.getApplicants().size());
        return jobRepository.save(job);
    }
}
