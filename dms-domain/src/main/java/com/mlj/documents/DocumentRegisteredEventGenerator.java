package com.mlj.documents;

public class DocumentRegisteredEventGenerator {

    public DocumentRegisteredEvent generateDocumentRegisteredEvent(String documentId, String title, String revision) {
        return new DocumentRegisteredEvent(documentId, null, title, revision);
    }
}
