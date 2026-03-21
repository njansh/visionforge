package com.visionforge.application.usecase;

import com.visionforge.application.port.JobEventPublisher;
import com.visionforge.domain.event.JobCreatedEvent;
import com.visionforge.domain.model.Job;
import com.visionforge.domain.repository.JobRepository;

public class CreateJobUseCase {
    private final JobRepository jobRepository;
    private final JobEventPublisher eventPublisher;

    public CreateJobUseCase(JobRepository jobRepository, JobEventPublisher eventPublisher) {
        this.jobRepository = jobRepository;
        this.eventPublisher = eventPublisher;
    }

    public Job execute(String originalImagePath) {

        Job job = Job.create(originalImagePath);

        Job savedJob = jobRepository.save(job);

        eventPublisher.publish(JobCreatedEvent.fromDomain(savedJob));

        return savedJob;
    }
}