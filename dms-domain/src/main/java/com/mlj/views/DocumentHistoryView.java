package com.mlj.views;

import com.mlj.documents.Document;
import com.mlj.documents.DocumentRepository;

import java.util.Collections;
import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

public class DocumentHistoryView {

    private final DocumentRepository documentRepository;

    public DocumentHistoryView(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public List<DocumentResource> history(String documentId) {
        List<DocumentResource> documentResources = newArrayList();
        for (Document document : documentRepository.historyFor(documentId)) {
            documentResources.add(new DocumentResource(dtoFor(document), Collections.<Link>emptySet()));
        }
        return documentResources;
    }

    private DocumentDto dtoFor(Document document) {
        return new DocumentDto(document.getDocumentId(), document.getDocumentNo(),
                document.getTitle(), document.getRevision());
    }

}
