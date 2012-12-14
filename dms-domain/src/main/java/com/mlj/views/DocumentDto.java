package com.mlj.views;

import com.mlj.documents.DocumentRegistered;
import com.mlj.documents.DocumentState;
import com.mlj.documents.DocumentSuperseded;
import com.mlj.events.Event;

import java.util.List;

public class DocumentDto {

    private String documentId;
    private String documentNo;
    private String title;
    private String revision;

    public DocumentDto(DocumentState documentState) {
        apply(documentState.getDocumentId(), documentState.getDocumentNo(), documentState.getTitle(),
                documentState.getRevision());
    }

    public DocumentDto(List<Event> events) {
        for (Event event : events) {
            if (event instanceof DocumentRegistered) {
                DocumentRegistered documentRegistered = (DocumentRegistered) event;
                apply(documentRegistered.getId(), documentRegistered.getDocumentNo(), documentRegistered.getTitle(),
                        documentRegistered.getRevision());
            } else if (event instanceof DocumentSuperseded) {
                DocumentSuperseded documentSuperseded = (DocumentSuperseded) event;
                apply(documentSuperseded.getId(), documentSuperseded.getDocumentNo(), documentSuperseded.getTitle(),
                        documentSuperseded.getRevision());
            }
        }
    }

    private void apply(String documentId, String documentNo, String title, String revision) {
        this.documentId = documentId;
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

}
