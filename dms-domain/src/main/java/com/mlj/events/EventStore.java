package com.mlj.events;

import static com.google.common.collect.Lists.newArrayList;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class EventStore {

    private final Multimap<String, Event> events = ArrayListMultimap.create();

    public void store(Event event) {
        events.put(event.getId(), event);
    }

    public List<Event> eventsFor(String id) {
        return newArrayList(events.get(id));
    }

    public Map<String, Collection<Event>> allEvents() {
        return events.asMap();
    }

}
