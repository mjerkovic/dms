package com.mlj.views;

import com.mlj.documents.Document;
import com.mlj.documents.DocumentRepository;

import java.util.ArrayList;
import java.util.List;

public class DocumentListView {

    private final DocumentRepository repository;

    public DocumentListView(DocumentRepository repository) {
        this.repository = repository;
    }

    public List<DocumentDto> list() {
        List<DocumentDto> result = new ArrayList<DocumentDto>();
        for (Document document : repository.allDocuments()) {
            result.add(dtoFor(document));
        }
        return result;
    }

    private DocumentDto dtoFor(Document document) {
        return new DocumentDto(document.getDocumentId(), document.getDocumentNo(),
                document.getTitle(), document.getRevision());
    }

}
