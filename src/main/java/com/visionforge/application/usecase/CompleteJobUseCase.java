package com.visionforge.application.usecase;

import com.visionforge.domain.exception.JobNotFoundException;
import com.visionforge.domain.model.Job;
import com.visionforge.domain.repository.JobRepository;

import java.util.UUID;

public class CompleteJobUseCase{
    private final JobRepository jobRepository;

    public CompleteJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public void execute(UUID jobId) {
        Job job = jobRepository.findById(jobId)
                .orElseThrow(() -> new JobNotFoundException(jobId));
        job.complete();
        jobRepository.save(job);
    }
}



