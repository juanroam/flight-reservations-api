package com.juanroam.reservations.notifier;

public interface NotifierObserver {

    void update(String message);

    NotifierType getType();
}
