package com.mlj.views;

import com.mlj.documents.DocumentRegistered;
import com.mlj.documents.DocumentState;
import com.mlj.documents.DocumentSuperseded;
import com.mlj.events.Event;
import com.mlj.events.EventStore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;

public class DocumentDtoRepository {

    private final EventStore eventStore;

    public DocumentDtoRepository(EventStore eventStore) {
        this.eventStore = eventStore;
    }

    public List<DocumentDto> allDocuments() {
        List<DocumentDto> result = new ArrayList<DocumentDto>();
        for (Map.Entry<String, Collection<Event>> events : eventStore.allEvents().entrySet()) {
            result.add(new DocumentDto(newArrayList(events.getValue())));
        }
        return result;
    }

    public List<DocumentDto> historyFor(String documentId) {
        List<DocumentDto> documentHistory = newArrayList();
        for (Event event : eventStore.eventsFor(documentId)) {
            DocumentState state = new DocumentState();
            if (event instanceof DocumentRegistered) {
                DocumentRegistered documentRegistered = (DocumentRegistered) event;
                documentHistory.add(new DocumentDto(new DocumentState(documentRegistered.getId(),
                        documentRegistered.getDocumentNo(), documentRegistered.getTitle(),
                        documentRegistered.getRevision(), 0)));
            } else if (event instanceof DocumentSuperseded) {
                DocumentSuperseded documentSuperseded = (DocumentSuperseded) event;
                documentHistory.add(new DocumentDto(new DocumentState(documentSuperseded.getId(),
                        documentSuperseded.getDocumentNo(), documentSuperseded.getTitle(),
                        documentSuperseded.getRevision(), 0)));
            }
        }
        return reverse(documentHistory);
    }

}
