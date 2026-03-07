package com.visionforge.infrastructure.event;

import com.visionforge.application.port.JobEventPublisher;
import com.visionforge.domain.event.JobCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class SpringJobEventPublisherAdapter implements JobEventPublisher {

    private final ApplicationEventPublisher springPublisher;

    public SpringJobEventPublisherAdapter(ApplicationEventPublisher springPublisher) {
        this.springPublisher = springPublisher;
    }

    @Override
    public void publish(JobCreatedEvent event) {
            springPublisher.publishEvent(event);
    }
}