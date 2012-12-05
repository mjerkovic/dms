package com.mlj.documents;

import com.mlj.events.Event;
import com.mlj.events.EventStore;

import java.util.*;

public class DocumentRepository {

    private final EventStore eventStore;

    public DocumentRepository(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public Document registerDocument(RegisterDocumentCommand registerDocumentCommand) {
        Document document = new Document(UUID.randomUUID().toString(), registerDocumentCommand.getDocumentNo(),
                registerDocumentCommand.getTitle(), registerDocumentCommand.getRevision());
        eventStore.store(new DocumentRegisteredEvent(document.getDocumentId(), document.getDocumentNo(),
                document.getTitle(), document.getRevision()));
        return document;
    }

    public Document supersedeDocument(SupersedeDocumentCommand supersedeDocumentCommand) {
        Document document = new Document(supersedeDocumentCommand.getDocumentId(), supersedeDocumentCommand.getDocumentNo(),
                supersedeDocumentCommand.getTitle(), supersedeDocumentCommand.getRevision());
        eventStore.store(new DocumentSupersededEvent(document.getDocumentId(), document.getDocumentNo(),
                document.getTitle(), document.getRevision()));
        return document;
    }

    public Document retrieveDocument(String id) {
        return createDocument(id, eventStore.eventsFor(id));
    }

    public List<Document> allDocuments() {
        List<Document> result = new ArrayList<Document>();
        for (Map.Entry<String, Collection<Event>> events : eventStore.allEvents().entrySet()) {
            result.add(createDocument(events.getKey(), events.getValue()));
        }
        return result;
    }

    private Document createDocument(String id, Collection<Event> events) {
        Document document = new Document(id);
        for (Event event : events) {
            event.applyTo(document);
        }
        document.markCommitted();
        return document;

    }

}
