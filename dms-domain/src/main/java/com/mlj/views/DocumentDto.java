package com.mlj.views;

public class DocumentDto {

    private final String documentId;
    private String documentNo;
    private String title;
    private String revision;

    public DocumentDto(String documentId, String documentNo, String title, String revision) {
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
