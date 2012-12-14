package com.mlj.documents;

import com.mlj.events.Event;
import com.mlj.events.EventObserver;
import com.mlj.events.EventStore;

public class PersistingEventObserver implements EventObserver {

    private final EventStore eventStore;

    public PersistingEventObserver(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public void notify(Event event) {
        eventStore.store(event);
    }
}
