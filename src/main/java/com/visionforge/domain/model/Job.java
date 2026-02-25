package com.visionforge.domain.model;

import com.visionforge.domain.enums.JobStatus;

import java.time.Instant;
import java.util.UUID;

public class Job {
    private UUID id;
    private JobStatus status;
    private Instant createdAt;
    private Instant finishedAt;
    private String failureReason;

    private Job(UUID id, JobStatus status, Instant createdAt) {
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Job create() {
        return new Job(UUID.randomUUID(), JobStatus.CREATED, Instant.now());
    }

    public UUID getId() { return id; }
    public JobStatus getStatus() { return status; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getFinishedAt() { return finishedAt; }
    public String getFailureReason() { return failureReason; }
}
