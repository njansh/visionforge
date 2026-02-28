package com.visionforge.domain.repository;

import com.visionforge.domain.model.Job;

import java.util.Optional;
import java.util.UUID;

public interface JobRepository {
    Job save(Job job);
    Optional<Job> findById(UUID id);
}
