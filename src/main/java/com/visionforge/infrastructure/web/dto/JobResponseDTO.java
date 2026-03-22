package com.visionforge.infrastructure.web.dto;

import com.visionforge.domain.model.Job;

import java.time.Instant;
import java.util.UUID;
public record JobResponseDTO(
        UUID id,
        String status,
        String createdAt,
        String finishedAt,
        String originalImagePath,
        String processedImagePath, // ADICIONAR ESTE
        String failureReason
) {
    public static JobResponseDTO fromDomain(Job job) {
        return new JobResponseDTO(
                job.getId(),
                job.getStatus().name(),
                job.getCreatedAt() != null ? job.getCreatedAt().toString() : null,
                job.getFinishedAt() != null ? job.getFinishedAt().toString() : null,
                job.getOriginalImagePath(),
                job.getProcessedImagePath(),
                job.getFailureReason()
        );
    }
}
