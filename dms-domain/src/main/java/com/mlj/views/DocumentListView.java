package com.mlj.views;

import static com.google.common.collect.Sets.newHashSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mlj.documents.Document;
import com.mlj.documents.DocumentRepository;

public class DocumentListView {

    private final DocumentRepository repository;

    public DocumentListView(DocumentRepository repository) {
        this.repository = repository;
    }

    public List<DocumentResource> list() {
        List<DocumentResource> result = new ArrayList<DocumentResource>();
        for (Document document : repository.allDocuments()) {
            result.add(new DocumentResource(dtoFor(document), linksFor(document)));
        }
        return result;
    }

    private DocumentDto dtoFor(Document document) {
        return new DocumentDto(document.getDocumentId(), document.getDocumentNo(),
                document.getTitle(), document.getRevision());
    }

    private Set<Link> linksFor(Document document) {
        return newHashSet(
                new Link("history", "http://localhost:8080/dms/docs/"+document.getDocumentId() + "/history"),
                new Link("supersede", "http://localhost:8080/dms/docs/"+document.getDocumentId())
        );
    }

}
