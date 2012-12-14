package com.mlj.documents;

import com.mlj.events.Event;

public class DocumentSuperseded extends Event {

    private String documentNo;
    private final String title;
    private final String revision;

    public DocumentSuperseded(String documentId, String documentNo, String title, String revision) {
        super(documentId);
        this.documentNo = documentNo;
        this.title = title;
        this.revision = revision;
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
