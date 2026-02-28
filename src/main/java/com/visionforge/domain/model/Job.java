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

    private Job(UUID id, JobStatus status, Instant createdAt) {
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
    }

    public static Job create() {
        return new Job(UUID.randomUUID(), JobStatus.CREATED, Instant.now());
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
}
