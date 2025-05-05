package com.juanroam.reservations.notifier;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

public class NotifierPublisher {

    private final List<NotifierObserver> notifiers;

    public NotifierPublisher() {
        this.notifiers = new ArrayList<>();
    }

    public void subscribe(NotifierObserver observer) {
        this.notifiers.add(observer);
    }

    public void unsubscribe(NotifierObserver observer) {
        this.notifiers.remove(observer);
    }

    public void notify(NotifierType notifierType, String message) {
        CompletableFuture.runAsync(() ->
            this.notifiers.stream()
                    .filter(n -> n.getType() == notifierType)
                    .findFirst()
                    .orElse(null)
                    .update(message));
    }


}
