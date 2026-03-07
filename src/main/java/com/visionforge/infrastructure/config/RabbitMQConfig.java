package com.visionforge.infrastructure.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String EXCHANGE_NAME = "visionforge.job.exchange";
    public static final String QUEUE_CREATED = "visionforge.job.created.queue";
    public static final String ROUTING_KEY_CREATED = "job.created";

    @Bean
    public DirectExchange jobExchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Queue jobCreatedQueue() {
        return new Queue(QUEUE_CREATED, true);
    }

    @Bean
    public Binding bindingJobCreated(Queue jobCreatedQueue, DirectExchange jobExchange) {
        return BindingBuilder.bind(jobCreatedQueue).to(jobExchange).with(ROUTING_KEY_CREATED);
    }
}