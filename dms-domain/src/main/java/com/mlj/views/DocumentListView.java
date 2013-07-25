package com.mlj.views;

import static com.google.common.collect.Sets.newHashSet;

import java.util.*;

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

    private Map<String, Link> linksFor(DocumentDto document) {
        Map<String, Link> links = new HashMap<String, Link>();
        links.put("history", new Link("history", "http://localhost:8080/dms/docs/" + document.getDocumentId() + "/history"));
        links.put("supersede", new Link("supersede", "http://localhost:8080/dms/docs/" + document.getDocumentId()));
        return links;
    }

}
