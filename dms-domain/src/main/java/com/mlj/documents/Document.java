package com.mlj.documents;

import static com.google.common.collect.Lists.newArrayList;

import java.util.List;

import com.google.common.base.Objects;
import com.mlj.events.Event;
import com.mlj.events.EventObserver;

public class Document {

    private final EventObserver eventObserver;
    private DocumentState documentState;

    public Document(EventObserver eventObserver) {
        this.eventObserver = eventObserver;
    }

    public Document(EventObserver eventObserver, List<? extends Event> events) {
        this(eventObserver);
        this.documentState = applyEvents(events);
    }

    public Document register(String documentId, RegisterDocumentCommand registerDocumentCommand) {
        return applyWithNotify(new DocumentRegistered(documentId, registerDocumentCommand.getDocumentNo(),
                registerDocumentCommand.getTitle(), registerDocumentCommand.getRevision()));
    }

    public Document supersede(SupersedeDocumentCommand supersedeDocumentCommand) {
        return applyWithNotify(new DocumentSuperseded(documentState.getDocumentId(),
                supersedeDocumentCommand.getDocumentNo(), supersedeDocumentCommand.getTitle(),
                supersedeDocumentCommand.getRevision()));
    }

    private Document applyWithNotify(DocumentRegistered event) {
        Document document = apply(event);
        eventObserver.notify(event);
        return document;
    }

    private  Document applyWithNotify(DocumentSuperseded event) {
        Document document = apply(event);
        eventObserver.notify(event);
        return document;
    }

    private Document apply(Event event) {
        return new Document(eventObserver, newArrayList(event));
    }

    private DocumentState applyEvents(List<? extends Event> events) {
        DocumentState state = new DocumentState();
        for (Event event : events) {
            if (event instanceof DocumentRegistered) {
                DocumentRegistered documentRegistered = (DocumentRegistered) event;
                state = new DocumentState(documentRegistered.getId(), documentRegistered.getDocumentNo(),
                        documentRegistered.getTitle(), documentRegistered.getRevision(), 0);
            } else if (event instanceof DocumentSuperseded) {
                DocumentSuperseded documentSuperseded = (DocumentSuperseded) event;
                state = new DocumentState(documentSuperseded.getId(), documentSuperseded.getDocumentNo(),
                        documentSuperseded.getTitle(), documentSuperseded.getRevision(), 0);
            }
        }
        return state;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("state", documentState).toString();
    }

}
