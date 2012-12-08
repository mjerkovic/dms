package com.mlj.documents;

import com.mlj.events.EventStore;
import org.junit.Before;
import org.junit.Test;

public class DocumentRepositoryTest {

    private DocumentRepository documentRepository;

    @Before
    public void givenADocumentRepository() {
        documentRepository = new DocumentRepository(new EventStore());
    }

    @Test
    public void itShouldRetrieveADocument() {
        /*RegisterDocumentCommand command = new RegisterDocumentCommand();
        command.setDocumentNo("DOC-000001");
        command.setTitle("First one");
        command.setRevision("A");
        Document expected = documentRepository.registerDocument(command);
        Document result = documentRepository.retrieveDocument(expected.getDocumentId());
        System.out.println("expected = " + expected);
        System.out.println("result = " + result);*/
    }
}
