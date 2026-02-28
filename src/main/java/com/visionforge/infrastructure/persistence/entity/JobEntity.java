package com.visionforge.infrastructure.persistence.entity;

import com.visionforge.domain.enums.JobStatus;
import com.visionforge.domain.model.Job;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.Instant;
import java.util.UUID;

@Entity
@Table(name = "jobs")
public class JobEntity {
    @Id
    private UUID id;

    @Enumerated(EnumType.STRING)
    private JobStatus status;
    private Instant createdAt;
    private Instant finishedAt;
    private String failureReason;

    public JobEntity() {
    }

    public static JobEntity fromDomain(Job job) {
        JobEntity entity = new JobEntity();
        entity.setId(job.getId());
        entity.setStatus(job.getStatus());
        entity.setCreatedAt(job.getCreatedAt());
        entity.setFinishedAt(job.getFinishedAt());
        entity.setFailureReason(job.getFailureReason());
        return entity;
    }

    public Job toDomain() {
        return Job.reconstruct(
                this.id,
                this.status,
                this.createdAt,
                this.finishedAt,
                this.failureReason
        );
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createdAt = createdAt;
    }

    public Instant getFinishedAt() {
        return finishedAt;
    }

    public void setFinishedAt(Instant finishedAt) {
        this.finishedAt = finishedAt;
    }

    public String getFailureReason() {
        return failureReason;
    }

    public void setFailureReason(String failureReason) {
        this.failureReason = failureReason;
    }
}
