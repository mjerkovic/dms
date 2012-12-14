package com.mlj.documents;

import java.util.UUID;

public class DocumentRegistrationService {

    private final DocumentRepository documentRepository;
    private final DocumentFactory documentFactory;

    public DocumentRegistrationService(DocumentRepository documentRepository,
                                       DocumentFactory documentFactory) {
        this.documentRepository = documentRepository;
        this.documentFactory = documentFactory;
    }

    public void registerDocument(RegisterDocumentCommand registerDocumentCommand) {
        documentFactory.createDocument().register( UUID.randomUUID().toString(), registerDocumentCommand);
    }

    public void supersedeDocument(String id, SupersedeDocumentCommand command) {
        documentRepository.retrieveDocument(id).supersede(command);
    }

}
