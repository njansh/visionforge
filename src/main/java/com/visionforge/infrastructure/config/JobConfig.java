package com.visionforge.infrastructure.config;

import com.visionforge.application.port.JobEventPublisher;
import com.visionforge.application.usecase.*;
import com.visionforge.domain.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Bean
    public CreateJobUseCase createJobUseCase(JobRepository jobRepository, JobEventPublisher eventPublisher) {
        return new CreateJobUseCase(jobRepository, eventPublisher);
    }
    @Bean
    public GetJobByIdUseCase getJobByIdUseCase(JobRepository jobRepository){
        return new GetJobByIdUseCase(jobRepository);
    }

    @Bean
    public StartJobUseCase startJobUseCase(JobRepository jobRepository) {
        return new StartJobUseCase(jobRepository);
    }

    @Bean
    public CompleteJobUseCase completeJobUseCase(JobRepository jobRepository) {
        return new CompleteJobUseCase(jobRepository);
    }

    @Bean
    public FailJobUseCase failJobUseCase(JobRepository jobRepository) {
        return new FailJobUseCase(jobRepository);
    }

}
