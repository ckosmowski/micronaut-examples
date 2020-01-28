package de.ksmwsk.deleteall;

import io.micronaut.configuration.dbmigration.flyway.event.MigrationFinishedEvent;
import io.micronaut.context.event.ApplicationEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.inject.Singleton;

@Singleton
public class EventListener implements ApplicationEventListener<MigrationFinishedEvent> {

    private static final Logger LOG = LoggerFactory.getLogger(EventListener.class);

    @Override
    public void onApplicationEvent(MigrationFinishedEvent event) {
        LOG.debug(event.toString());
    }
}
