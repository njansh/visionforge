package com.visionforge.infrastructure.config;

import com.visionforge.application.usecase.CreateJobUseCase;
import com.visionforge.application.usecase.GetJobByIdUseCase;
import com.visionforge.domain.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobConfig {

    @Bean
    public CreateJobUseCase createJobUseCase(JobRepository jobRepository) {
        return new CreateJobUseCase(jobRepository);
    }
    @Bean
    public GetJobByIdUseCase getJobByIdUseCase(JobRepository jobRepository){
        return new GetJobByIdUseCase(jobRepository);
    }
}