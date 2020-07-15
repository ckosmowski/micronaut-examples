package de.ksmwsk;

import io.micronaut.configuration.kafka.annotation.KafkaClient;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;

@Named
@Singleton
public class KafkaProducer {

    private final Producer<String, String> kafkaProducer;

    private static final Logger LOG = LoggerFactory.getLogger(KafkaProducer.class);

    @Inject
    public KafkaProducer(@KafkaClient("dpx-lwisinterface-client") Producer<String, String> kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    public void sendMessage(String test) {
       // send message
    }
}