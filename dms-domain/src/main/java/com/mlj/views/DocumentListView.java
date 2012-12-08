package com.mlj.views;

import com.mlj.documents.Document;
import com.mlj.documents.DocumentRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static com.google.common.collect.Sets.newHashSet;

public class DocumentListView {

    private final DocumentRepository repository;

    public DocumentListView(DocumentRepository repository) {
        this.repository = repository;
    }

    public List<DocumentResource> list() {
        List<DocumentResource> result = new ArrayList<DocumentResource>();
        for (Document document : repository.allDocuments()) {
            result.add(new DocumentResource(dtoFor(document), historyLinkFor(document)));
        }
        return result;
    }

    private DocumentDto dtoFor(Document document) {
        return new DocumentDto(document.getDocumentId(), document.getDocumentNo(),
                document.getTitle(), document.getRevision());
    }

    private Set<Link> historyLinkFor(Document document) {
        return newHashSet(new Link("history", "http://localhost:8080/dms/docs/"+document.getDocumentId() + "/history"));
    }

}
