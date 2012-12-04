package com.mlj.documents;

import com.mlj.events.Event;

public class DocumentRegisteredEvent extends Event<Document> {

    private String documentNo;
    private final String title;
    private final String revision;

    public DocumentRegisteredEvent(String documentId, String documentNo, String title, String revision) {
        super(documentId);
        this.documentNo = documentNo;
        this.title = title;
        this.revision = revision;
    }

    @Override
    public void applyTo(Document object) {
        object.apply(this);
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

    public String getEventName() {
        return getClass().getName();
    }

}
