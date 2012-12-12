package com.mlj.events;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class EventStore {

    private final Multimap<String, Event> events = ArrayListMultimap.create();

    public void store(List<Event> newEvents) {
        for (Event event : newEvents) {
            events.put(event.getId(), event);
        }
    }

    public Collection<Event> eventsFor(String id) {
        return events.get(id);
    }

    public Map<String, Collection<Event>> allEvents() {
        return events.asMap();
    }

}
