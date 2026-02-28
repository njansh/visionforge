package com.visionforge.application.usecase;

import com.visionforge.domain.model.Job;
import com.visionforge.domain.repository.JobRepository;

public class CreateJobUseCase {
    private final JobRepository jobRepository;

    public CreateJobUseCase(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    public Job execute() {
        Job job = Job.create();
        return jobRepository.save(job);
    }

}

