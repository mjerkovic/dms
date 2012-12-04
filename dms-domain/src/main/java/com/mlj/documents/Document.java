package com.mlj.documents;

import com.google.common.base.Objects;
import com.google.common.collect.Lists;
import com.mlj.events.Event;

import java.util.List;

public class Document {

    private final String documentId;
    private String documentNo;
    private String title;
    private String revision;
    private int version;

    private List<Event> uncommittedEvents = Lists.newArrayList();

    public Document(String documentId) {
        this.documentId = documentId;
    }
    public Document(String documentId, String documentNo, String title, String revision) {
        this(documentId);
        this.documentNo = documentNo;
        this.title = title;
        this.revision = revision;
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

    public void apply(DocumentRegisteredEvent event) {
        this.documentNo = event.getDocumentNo();
        this.title = event.getTitle();
        this.revision = event.getRevision();
        uncommittedEvents.add(event);
    }

    public void apply(DocumentSupersededEvent event) {
        this.documentNo = event.getDocumentNo();
        this.title = event.getTitle();
        this.revision = event.getRevision();
        uncommittedEvents.add(event);
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
