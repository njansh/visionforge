package com.visionforge.domain.model;

import com.visionforge.domain.enums.JobStatus;
import com.visionforge.domain.exception.InvalidJobStateTransitionException;

import java.time.Instant;
import java.util.UUID;

public class Job {
    private UUID id;
    private JobStatus status;
    private Instant createdAt;
    private Instant finishedAt;
    private String failureReason;
    private String originalImagePath;
    private String processedImagePath;

    private Job(UUID id, JobStatus status, Instant createdAt) {
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
    }
    public static Job reconstruct(UUID id, JobStatus status, Instant createdAt, Instant finishedAt, String failureReason, String originalImagePath, String processedImagePath) {
        Job job = new Job(id, status, createdAt);
        job.finishedAt = finishedAt;
        job.failureReason = failureReason;
        job.originalImagePath = originalImagePath;
        job.processedImagePath = processedImagePath;
        return job;
    }

    public static Job create(String originalImagePath) {
        Job job = new Job(UUID.randomUUID(), JobStatus.CREATED, Instant.now());
        job.originalImagePath = originalImagePath;
        return job;
    }


    public void start() {
        if (this.status != JobStatus.CREATED) {
            throw new InvalidJobStateTransitionException(this.status, JobStatus.RUNNING);
        }
        this.status = JobStatus.RUNNING;
    }

    public void complete() {
        if (this.status != JobStatus.RUNNING) {
            throw new InvalidJobStateTransitionException(this.status, JobStatus.DONE);
        }
        this.status = JobStatus.DONE;
        this.finishedAt = Instant.now();
    }


    public void fail(String reason) {
        if (this.status != JobStatus.CREATED && this.status != JobStatus.RUNNING) {
            throw new InvalidJobStateTransitionException(this.status, JobStatus.FAILED);
        }

        if (reason == null || reason.isBlank()) {
            throw new InvalidJobStateTransitionException(this.status, JobStatus.FAILED);
        }

        this.status = JobStatus.FAILED;
        this.failureReason = reason;
        this.finishedAt = Instant.now();
    }

    public UUID getId() { return id; }
    public JobStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getFinishedAt() { return finishedAt; }
    public String getFailureReason() { return failureReason; }
    public String getOriginalImagePath() { return originalImagePath; }
    public void setOriginalImagePath(String originalImagePath) { this.originalImagePath = originalImagePath; }
    public String getProcessedImagePath() { return processedImagePath; }
    public void setProcessedImagePath(String processedImagePath) { this.processedImagePath = processedImagePath; }
}

