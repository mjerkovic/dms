package com.mlj.documents;

import com.mlj.events.EventPublisher;

public class DocumentRegistrationService {

    private final DocumentRepository documentRepository;
    private final EventPublisher eventPublisher;

    public DocumentRegistrationService(DocumentRepository documentRepository, EventPublisher eventPublisher) {
        this.documentRepository = documentRepository;
        this.eventPublisher = eventPublisher;
    }

    public void registerDocument(RegisterDocumentCommand registerDocumentCommand) {
        Document document = documentRepository.registerDocument(registerDocumentCommand);
        eventPublisher.publish(new DocumentRegisteredEvent(document.getDocumentId(), document.getDocumentNo(),
                document.getTitle(), document.getRevision()));
    }
}
