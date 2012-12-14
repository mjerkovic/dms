package com.mlj.documents;

public class DocumentState {

    private String documentId;
    private String documentNo;
    private String title;
    private String revision;
    private int version;

    public DocumentState() {
    }

    public DocumentState(String documentId, String documentNo, String title, String revision, int version) {
        this.documentId = documentId;
        this.documentNo = documentNo;
        this.title = title;
        this.revision = revision;
        this.version = version;
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
