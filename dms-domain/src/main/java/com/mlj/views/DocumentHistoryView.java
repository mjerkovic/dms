package com.mlj.views;

import com.google.common.collect.Maps;

import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class DocumentHistoryView {

    private final DocumentDtoRepository repository;

    public DocumentHistoryView(DocumentDtoRepository repository) {
        this.repository = repository;
    }

    public List<DocumentResource> history(String documentId) {
        List<DocumentResource> documentResources = newArrayList();
        for (DocumentDto document : repository.historyFor(documentId)) {
            documentResources.add(new DocumentResource(document, Maps.<String, Link>newHashMap()));
        }
        return documentResources;
    }

}
