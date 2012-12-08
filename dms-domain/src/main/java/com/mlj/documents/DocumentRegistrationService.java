package com.mlj.documents;

import com.mlj.events.EventStore;

public class DocumentRegistrationService {

    private final DocumentRepository documentRepository;

    public DocumentRegistrationService(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
    }

    public void registerDocument(RegisterDocumentCommand registerDocumentCommand) {
        documentRepository.registerDocument(registerDocumentCommand);
    }

    public void supersedeDocument(String id, SupersedeDocumentCommand command) {
        documentRepository.supersedeDocument(id, command);
    }

}
