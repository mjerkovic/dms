package com.mlj.events;

import static com.google.common.collect.Lists.newArrayList;

import java.util.*;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

public class EventStore {

    private final LinkedList<Event> events = new LinkedList<Event>();
    private final Multimap<String, Event> documentEvents = ArrayListMultimap.create();

    public void store(Event event) {
        events.push(event);
        documentEvents.put(event.getId(), event);
    }

    public List<Event> eventsFor(String id) {
        return newArrayList(documentEvents.get(id));
    }

    public Map<String, Collection<Event>> allEvents() {
        return documentEvents.asMap();
    }

}
