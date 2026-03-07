package com.visionforge.domain.event;

import java.time.Instant;
import java.util.UUID;

import com.visionforge.domain.model.Job;

public record JobCreatedEvent(UUID jobId,
                              Instant occurredOn ) {
    public static JobCreatedEvent fromDomain(Job job) {
        return new JobCreatedEvent(job.getId(), job.getCreatedAt());
    }
}
