package com.visionforge.infrastructure.web.controller;

import com.visionforge.application.usecase.*;
import com.visionforge.domain.model.Job;
import com.visionforge.infrastructure.storage.LocalFileStorageService; // IMPORTANTE
import com.visionforge.infrastructure.web.dto.JobResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType; // IMPORTANTE
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile; // IMPORTANTE

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/jobs")
public class JobController {

    private final CreateJobUseCase createJobUseCase;
    private final GetJobByIdUseCase getJobByIdUseCase;
    private final StartJobUseCase startJobUseCase;
    private final CompleteJobUseCase completeJobUseCase;
    private final FailJobUseCase failJobUseCase;
    private final LocalFileStorageService storageService;


    // CONSTRUTOR ATUALIZADO
    public JobController(CreateJobUseCase createJobUseCase,
                         GetJobByIdUseCase getJobByIdUseCase,
                         StartJobUseCase startJobUseCase,
                         CompleteJobUseCase completeJobUseCase,
                         FailJobUseCase failJobUseCase,
                         LocalFileStorageService storageService) {
        this.createJobUseCase = createJobUseCase;
        this.getJobByIdUseCase = getJobByIdUseCase;
        this.startJobUseCase = startJobUseCase;
        this.completeJobUseCase = completeJobUseCase;
        this.failJobUseCase = failJobUseCase;
        this.storageService = storageService;
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<JobResponseDTO> create(@RequestParam("file") MultipartFile file) {

        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        String savedImagePath = storageService.saveImage(file, uniqueFileName);

        Job job = createJobUseCase.execute(savedImagePath);

        JobResponseDTO responseDTO = JobResponseDTO.fromDomain(job);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{jobId}")
    public ResponseEntity<JobResponseDTO> get(@PathVariable UUID jobId) {
        Job job = getJobByIdUseCase.execute(jobId);
        JobResponseDTO responseDTO = JobResponseDTO.fromDomain(job);
        return ResponseEntity.ok(responseDTO);
    }

    @PatchMapping("/{jobId}/start")
    public ResponseEntity<JobResponseDTO> start(@PathVariable UUID jobId) {
        startJobUseCase.execute(jobId);
        Job job = getJobByIdUseCase.execute(jobId);
        return ResponseEntity.ok(JobResponseDTO.fromDomain(job));
    }

    @PatchMapping("/{jobId}/complete")
    public ResponseEntity<JobResponseDTO> complete(@PathVariable UUID jobId) {
        completeJobUseCase.execute(jobId);
        Job job = getJobByIdUseCase.execute(jobId);
        return ResponseEntity.ok(JobResponseDTO.fromDomain(job));
    }

    @PatchMapping("/{jobId}/fail")
    public ResponseEntity<JobResponseDTO> fail(
            @PathVariable UUID jobId,
            @RequestBody String reason
    ) {
        failJobUseCase.execute(jobId, reason);
        Job job = getJobByIdUseCase.execute(jobId);
        return ResponseEntity.ok(JobResponseDTO.fromDomain(job));
    }
}