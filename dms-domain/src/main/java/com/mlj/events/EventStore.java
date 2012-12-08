package com.mlj.events;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.mlj.documents.Document;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class EventStore {

    private final Multimap<String, Event> events = ArrayListMultimap.create();

    public void store(Document document) {
        for (Event event : document.getUncommittedEvents()) {
            events.put(event.getId(), event);
        }
        document.markCommitted();
    }

    public Collection<Event> eventsFor(String id) {
        return events.get(id);
    }

    public Map<String, Collection<Event>> allEvents() {
        return events.asMap();
    }

}
