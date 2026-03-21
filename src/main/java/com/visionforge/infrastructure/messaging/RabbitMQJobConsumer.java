package com.visionforge.infrastructure.messaging;

import com.visionforge.application.usecase.CompleteJobUseCase;
import com.visionforge.application.usecase.FailJobUseCase;
import com.visionforge.application.usecase.GetJobByIdUseCase;
import com.visionforge.application.usecase.StartJobUseCase;
import com.visionforge.domain.model.Job;
import com.visionforge.infrastructure.processing.ImageProcessingService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitMQJobConsumer {

    private final StartJobUseCase startJobUseCase;
    private final CompleteJobUseCase completeJobUseCase;
    private final GetJobByIdUseCase getJobByIdUseCase;
    private final FailJobUseCase failJobUseCase;
    private final ImageProcessingService imageProcessingService;

    public RabbitMQJobConsumer(StartJobUseCase startJobUseCase,
                               CompleteJobUseCase completeJobUseCase,
                               GetJobByIdUseCase getJobByIdUseCase,
                               FailJobUseCase failJobUseCase,
                               ImageProcessingService imageProcessingService) {
        this.startJobUseCase = startJobUseCase;
        this.completeJobUseCase = completeJobUseCase;
        this.getJobByIdUseCase = getJobByIdUseCase;
        this.failJobUseCase = failJobUseCase;
        this.imageProcessingService = imageProcessingService;
    }

    @RabbitListener(queues = "visionforge.job.created.queue")
    public void processJob(String jobIdStr) {
        UUID jobId = UUID.fromString(jobIdStr);
        System.out.println("\n[Worker] 🚀 New message received from queue! Job ID: " + jobId);

        try {
            startJobUseCase.execute(jobId);
            System.out.println("[Worker] ⏳ Job " + jobId + " updated to RUNNING. Starting processing...");

            Job job = getJobByIdUseCase.execute(jobId);
            String originalImagePath = job.getOriginalImagePath();

            String processedImagePath = imageProcessingService.applyGrayscaleFilter(originalImagePath);
            System.out.println("[Worker] 🎨 Filter applied successfully! New image saved at: " + processedImagePath);

            completeJobUseCase.execute(jobId, processedImagePath);
            System.out.println("[Worker] ✅ Processing completed! Job updated to DONE!\n");

        } catch (Exception e) {
            System.err.println("[Worker] ❌ Error processing Job " + jobId + ": " + e.getMessage());
            failJobUseCase.execute(jobId, e.getMessage());
        }
    }
}
