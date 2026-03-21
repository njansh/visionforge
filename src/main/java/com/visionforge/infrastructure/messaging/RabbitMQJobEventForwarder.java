package com.visionforge.infrastructure.messaging;

import com.visionforge.domain.event.JobCreatedEvent;
import com.visionforge.infrastructure.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class RabbitMQJobEventForwarder {

    private final RabbitTemplate rabbitTemplate;

    public RabbitMQJobEventForwarder(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }


    @EventListener
    public void handleJobCreatedEvent(JobCreatedEvent event) {


        String message = event.jobId().toString();

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE_NAME,
                RabbitMQConfig.ROUTING_KEY_CREATED,
                message
        );

        System.out.println("[Event Forwarder] Event captured! ID sent to RabbitMQ: " + message);
    }
}