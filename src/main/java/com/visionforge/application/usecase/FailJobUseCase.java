package com.visionforge.application.usecase;

import com.visionforge.domain.model.Job;
import com.visionforge.domain.repository.JobRepository;

import java.util.UUID;

public class FailJobUseCase{
    private final JobRepository jobRepository;

    public FailJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void execute(UUID jobId, String reason) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new IllegalArgumentException("Job not found with ID: " + jobId));

        job.fail(reason);
        jobRepository.save(job);
    }
}





