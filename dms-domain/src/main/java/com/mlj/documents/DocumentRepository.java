package com.mlj.documents;

import com.mlj.events.Event;
import com.mlj.events.EventStore;

import java.util.UUID;

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
        Document document = new Document(id);
        for (Event event : eventStore.eventsFor(id)) {
            event.applyTo(document);
        }
        document.markCommitted();
        return document;
    }

}
