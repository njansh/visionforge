package com.visionforge.infrastructure.persistence.repository;

import com.visionforge.infrastructure.persistence.entity.JobEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpringDataJobRepository extends JpaRepository<JobEntity, UUID> {
}
