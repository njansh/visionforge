package com.visionforge.application.usecase;

import com.visionforge.domain.exception.JobNotFoundException;
import com.visionforge.domain.model.Job;
import com.visionforge.domain.repository.JobRepository;

import java.util.UUID;

public class StartJobUseCase {
    private final JobRepository jobRepository;

    public StartJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void execute(UUID jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException(jobId));
        job.start();
        jobRepository.save(job);
    }
}

