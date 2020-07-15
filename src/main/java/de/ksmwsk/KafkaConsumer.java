package de.ksmwsk;

import io.micronaut.configuration.kafka.annotation.KafkaListener;
import io.micronaut.configuration.kafka.annotation.OffsetReset;
import io.micronaut.configuration.kafka.annotation.OffsetStrategy;
import io.micronaut.configuration.kafka.annotation.Topic;

import javax.inject.Inject;

@KafkaListener(batch = true, groupId = "test", offsetReset = OffsetReset.EARLIEST, offsetStrategy = OffsetStrategy.SYNC)
public class KafkaConsumer {

    private KafkaProducer adapter;

    @Inject
    public KafkaConsumer(KafkaProducer adapter) {
        this.adapter = adapter;
    }

    @Topic("testTopic")
    public void receiveLwisMigrationNotification() {
    }

}
