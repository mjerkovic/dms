package com.mlj.documents;

import java.util.List;

import com.mlj.events.Event;
import com.mlj.events.EventObserver;

public class DocumentFactory {

    private final EventObserver eventObserver;

    public DocumentFactory(EventObserver eventObserver) {
        this.eventObserver = eventObserver;
    }

    public Document createDocument() {
        return new Document(eventObserver);
    }

    public Document createDocumentWith(List<Event> events) {
        return new Document(eventObserver, events);
    }
}
