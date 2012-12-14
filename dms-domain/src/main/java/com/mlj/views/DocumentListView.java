package com.mlj.views;

import static com.google.common.collect.Sets.newHashSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mlj.documents.Document;
import com.mlj.documents.DocumentRepository;

public class DocumentListView {

    private final DocumentDtoRepository repository;

    public DocumentListView(DocumentDtoRepository repository) {
        this.repository = repository;
    }

    public List<DocumentResource> list() {
        List<DocumentResource> result = new ArrayList<DocumentResource>();
        for (DocumentDto document : repository.allDocuments()) {
            result.add(new DocumentResource(document, linksFor(document)));
        }
        return result;
    }

    private Set<Link> linksFor(DocumentDto document) {
        return newHashSet(
                new Link("history", "http://localhost:8080/dms/docs/"+document.getDocumentId() + "/history"),
                new Link("supersede", "http://localhost:8080/dms/docs/"+document.getDocumentId())
        );
    }

}
