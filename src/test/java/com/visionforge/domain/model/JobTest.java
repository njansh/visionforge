package com.visionforge.domain.model;

import com.visionforge.domain.enums.JobStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;



import static org.junit.jupiter.api.Assertions.*;

class JobTest {

    @Test
    @DisplayName("Deve criar um novo Job com status CREATED")
    void shouldCreateJobWithInitialStatus() {
        String originalPath = "uploads/test.jpg";
        Job job = Job.create(originalPath);

        assertNotNull(job.getId());
        assertEquals(JobStatus.CREATED, job.getStatus());
        assertEquals(originalPath, job.getOriginalImagePath());
        assertNotNull(job.getCreatedAt());
        assertNull(job.getProcessedImagePath());
    }

    @Test
    @DisplayName("Deve mudar para RUNNING ao iniciar o job")
    void shouldChangeStatusToRunning() {
        Job job = Job.create("path/to/image.png");
        job.start();

        assertEquals(JobStatus.RUNNING, job.getStatus());
    }

    @Test
    @DisplayName("Deve mudar para DONE e definir data de término ao completar")
    void shouldChangeStatusToDoneWhenCompleted() {
        Job job = Job.create("path/to/image.png");
        job.start();

        String processedPath = "uploads/processed_test.png";
        job.complete(processedPath);

        assertEquals(JobStatus.DONE, job.getStatus());
        assertEquals(processedPath, job.getProcessedImagePath());
        assertNotNull(job.getFinishedAt());
    }
}