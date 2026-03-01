package com.visionforge.infrastructure.web.controller;

import com.visionforge.application.usecase.CreateJobUseCase;
import com.visionforge.application.usecase.GetJobByIdUseCase;
import com.visionforge.domain.model.Job;
import com.visionforge.infrastructure.web.dto.JobResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final CreateJobUseCase createJobUseCase;
    private final GetJobByIdUseCase getJobByIdUseCase;

    public JobController(CreateJobUseCase createJobUseCase, GetJobByIdUseCase getJobByIdUseCase) {
        this.createJobUseCase = createJobUseCase;
        this.getJobByIdUseCase = getJobByIdUseCase;
    }

    @PostMapping
    public ResponseEntity<JobResponseDTO> create() {
        Job job = createJobUseCase.execute();

        JobResponseDTO responseDTO = JobResponseDTO.fromDomain(job);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }
    @GetMapping("/{jobId}")
    public ResponseEntity<JobResponseDTO> get(@PathVariable UUID jobId) {
        Job job = getJobByIdUseCase.execute(jobId);

        JobResponseDTO responseDTO = JobResponseDTO.fromDomain(job);

        return ResponseEntity.ok(responseDTO);
    }
}
