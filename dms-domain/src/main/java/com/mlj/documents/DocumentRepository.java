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

}
