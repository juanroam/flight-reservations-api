package com.juanroam.reservations.notifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EmailNotifier implements NotifierObserver {

    private static final Logger LOGGER = LoggerFactory.getLogger(EmailNotifier.class);

    @Override
    public void update(String message) {
        // TODO: send to messaging queue
        LOGGER.info("Sending email notification for reservation with ID {}", message);
    }

    @Override
    public NotifierType getType() {
        return NotifierType.EMAIL_NOTIFIER;
    }
}
