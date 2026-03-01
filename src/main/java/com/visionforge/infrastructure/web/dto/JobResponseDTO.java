package com.visionforge.infrastructure.web.dto;

import com.visionforge.domain.model.Job;

import java.time.Instant;
import java.util.UUID;

public record JobResponseDTO(
        UUID id,
        String status,
        Instant createdAt,
        Instant finishedAt,
        String failureReason
) {
    public static JobResponseDTO fromDomain(Job job) {
        return new JobResponseDTO(
                job.getId(),
                job.getStatus().name(),
                job.getCreatedAt(),
                job.getFinishedAt(),
                job.getFailureReason()
        );
    }
}
