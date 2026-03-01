package com.visionforge.application.usecase;

import com.visionforge.domain.model.Job;
import com.visionforge.domain.repository.JobRepository;

import java.util.UUID;

public class GetJobByIdUseCase {

    private final JobRepository jobRepository;

    public GetJobByIdUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job execute(UUID jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Job not found with ID: " + jobId));
        return job;
    }
}
