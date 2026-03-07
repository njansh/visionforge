package com.visionforge.application.port;

import com.visionforge.domain.event.JobCreatedEvent;

public interface JobEventPublisher {
    void publish(JobCreatedEvent event);
}