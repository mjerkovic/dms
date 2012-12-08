package com.mlj.documents;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.mlj.events.Event;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class Document {

    private final String documentId;
    private String documentNo;
    private String title;
    private String revision;
    private int version;

    private List<Event> uncommittedEvents = newArrayList();

    public Document(String documentId) {
        this.documentId = documentId;
    }

    public Document(String documentId, String documentNo, String title, String revision) {
        this(documentId);
        this.documentNo = documentNo;
        this.title = title;
        this.revision = revision;
    }

    public Document(String documentId, String documentNo, String title, String revision, List<Event> events) {
        this(documentId, documentNo, title, revision);
        this.uncommittedEvents = newArrayList(events);
    }

    public String getDocumentId() {
        return documentId;
    }

    public String getDocumentNo() {
        return documentNo;
    }

    public String getTitle() {
        return title;
    }

    public String getRevision() {
        return revision;
    }

    public List<Event> getUncommittedEvents() {
        return newArrayList(uncommittedEvents);
    }

    public Document register(RegisterDocumentCommand registerDocumentCommand) {
        return apply(new DocumentRegisteredEvent(documentId, registerDocumentCommand.getDocumentNo(),
                registerDocumentCommand.getTitle(), registerDocumentCommand.getRevision()));
    }

    public Document supersede(SupersedeDocumentCommand supersedeDocumentCommand) {
        return apply(new DocumentSupersededEvent(documentId, supersedeDocumentCommand.getDocumentNo(),
                supersedeDocumentCommand.getTitle(), supersedeDocumentCommand.getRevision()));
    }


    public Document apply(DocumentRegisteredEvent event) {
        Document document = new Document(documentId, event.getDocumentNo(), event.getTitle(), event.getRevision(), uncommittedEvents);
        document.uncommittedEvents.add(event);
        return document;
    }

    public Document apply(DocumentSupersededEvent event) {
        Document document = new Document(documentId, event.getDocumentNo(), event.getTitle(), event.getRevision(), uncommittedEvents);
        document.uncommittedEvents.add(event);
        return document;
    }

    public void markCommitted() {
        uncommittedEvents.clear();
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("id", documentId).add("documentNo", documentNo).add("title", title)
                .add("revision", revision).add("events", uncommittedEvents).toString();
    }

}
