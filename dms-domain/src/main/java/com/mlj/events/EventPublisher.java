package com.mlj.events;

public class EventPublisher {

    private final EventStore eventStore;

    public EventPublisher(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public void publish(Event event) {
        eventStore.store(event);
    }

}
