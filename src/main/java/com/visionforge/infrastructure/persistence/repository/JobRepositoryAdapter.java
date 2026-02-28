package com.visionforge.infrastructure.persistence.repository;

import com.visionforge.domain.model.Job;
import com.visionforge.domain.repository.JobRepository;
import com.visionforge.infrastructure.persistence.entity.JobEntity;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public class JobRepositoryAdapter implements JobRepository {

    private final SpringDataJobRepository springDataRepository;

    public JobRepositoryAdapter(SpringDataJobRepository springDataRepository) {
        this.springDataRepository = springDataRepository;
    }

    @Override
    public Job save(Job job) {
        JobEntity entity = JobEntity.fromDomain(job);
        JobEntity savedEntity = springDataRepository.save(entity);
        return savedEntity.toDomain();
    }

    @Override
    public Optional<Job> findById(UUID id) {

        Optional<JobEntity> entityOptional = springDataRepository.findById(id);
        return entityOptional.map(entity -> entity.toDomain());
    }
}