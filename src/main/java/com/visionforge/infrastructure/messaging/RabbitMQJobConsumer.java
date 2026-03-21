package com.visionforge.infrastructure.messaging;

import com.visionforge.application.usecase.StartJobUseCase;
import com.visionforge.application.usecase.CompleteJobUseCase;
import com.visionforge.infrastructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class RabbitMQJobConsumer {

    private final StartJobUseCase startJobUseCase;
    private final CompleteJobUseCase completeJobUseCase;

    public RabbitMQJobConsumer(StartJobUseCase startJobUseCase, CompleteJobUseCase completeJobUseCase) {
        this.startJobUseCase = startJobUseCase;
        this.completeJobUseCase = completeJobUseCase;
    }

    @RabbitListener(queues = RabbitMQConfig.QUEUE_CREATED)
    public void consumeJobCreatedMessage(String message) {
        System.out.println("\n[Worker] 🚀 New message received from queue! Job ID: " + message);

        try {
            UUID jobId = UUID.fromString(message);

            startJobUseCase.execute(jobId);
            System.out.println("[Worker] ⏳ Job " + jobId + " updated to RUNNING. Starting image processing...");

            Thread.sleep(5000);

            completeJobUseCase.execute(jobId);
            System.out.println("[Worker] ✅ Processing completed! Job " + jobId + " updated to DONE!");

        } catch (Exception e) {
            System.err.println("[Worker] ❌ Error processing Job " + message + ": " + e.getMessage());
            throw new RuntimeException("Failure processing RabbitMQ event", e);
        }
    }
}
