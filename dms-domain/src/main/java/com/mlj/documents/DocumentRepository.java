package com.mlj.documents;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import com.mlj.events.Event;
import com.mlj.events.EventStore;

public class DocumentRepository {

    private final EventStore eventStore;
    private final DocumentFactory documentFactory;

    public DocumentRepository(EventStore eventStore, DocumentFactory documentFactory) {
        this.eventStore = eventStore;
        this.documentFactory = documentFactory;
    }

    public Document retrieveDocument(String id) {
        return documentFactory.createDocumentWith(eventStore.eventsFor(id));
    }

    public List<Document> allDocuments() {
        List<Document> result = new ArrayList<Document>();
        for (Map.Entry<String, Collection<Event>> events : eventStore.allEvents().entrySet()) {
            result.add(documentFactory.createDocumentWith(newArrayList(events.getValue())));
        }
        return result;
    }

    public List<Document> historyFor(String documentId) {
        List<Document> documentHistory = newArrayList();
        Document document = new Document(documentId);
        for (Event event : eventStore.eventsFor(documentId)) {
            if (event instanceof DocumentRegistered) {
                document = document.apply((DocumentRegistered) event);
            } else if (event instanceof DocumentSuperseded) {
                document = document.apply((DocumentSuperseded) event);
            }
            document.markCommitted();
            documentHistory.add(document);
        }
        return reverse(documentHistory);
    }


}
