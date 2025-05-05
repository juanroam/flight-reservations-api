package com.juanroam.reservations.config;

import com.juanroam.reservations.notifier.EmailNotifier;
import com.juanroam.reservations.notifier.NotifierPublisher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public EmailNotifier emailNotifier() {
        return new EmailNotifier();
    }

    @Bean
    public NotifierPublisher notifierPublisher(EmailNotifier emailNotifier) {
        NotifierPublisher notifierPublisher = new NotifierPublisher();
        notifierPublisher.subscribe(emailNotifier);
        return notifierPublisher;
    }
}
