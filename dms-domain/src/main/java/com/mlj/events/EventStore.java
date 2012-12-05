package com.mlj.events;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class EventStore {

    private final Multimap<String, Event> events = ArrayListMultimap.create();

    public void store(Event event) {
        events.put(event.getId(), event);
    }

    public Collection<Event> eventsFor(String id) {
        return events.get(id);
    }

    public Map<String, Collection<Event>> allEvents() {
        return events.asMap();
    }

}
