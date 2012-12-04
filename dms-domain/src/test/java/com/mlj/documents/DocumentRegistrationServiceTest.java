package com.mlj.documents;

import com.google.common.collect.Lists;
import com.mlj.events.EventPublisher;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DocumentRegistrationServiceTest {

    private DocumentRegistrationService registrationService;
    private DocumentRepository documentRepository;
    private EventPublisher eventPublisher;

    @Before
    public void givenADocumentRegistrationService() {
        documentRepository = mock(DocumentRepository.class);
        eventPublisher = mock(EventPublisher.class);
        registrationService = new DocumentRegistrationService(documentRepository, eventPublisher);
    }

    @Test
    public void testMeToo() {
        List<Owner> owners = Lists.newArrayList(new Ben(), new Jerry());
        IceCream iceCream = new IceCream();
        for (Owner o : owners) {
          o.applyTo(iceCream);
        }
    }

    private class IceCream {
        public void apply(Ben event) {
            System.out.println("event = " + event);
        }

        public void apply(Jerry event) {
            System.out.println("event = " + event);
        }
    }

    private class Ben extends Owner {

        @Override
        public void applyTo(IceCream iceCream) {
            iceCream.apply(this);
        }
    }

    private class Jerry extends Owner {

        @Override
        public void applyTo(IceCream iceCream) {
            iceCream.apply(this);
        }
    }

    private abstract class Owner {
      public abstract void applyTo(IceCream iceCream);
    }

    @Test
    public void itShouldRegisterTheDocument() {
        RegisterDocumentCommand registerDocumentCommand = new RegisterDocumentCommand();
        Document document = mock(Document.class);
        when(documentRepository.registerDocument(registerDocumentCommand)).thenReturn(document);

        registrationService.registerDocument(registerDocumentCommand);

        verify(documentRepository).registerDocument(registerDocumentCommand);
    }

    @Test
    public void testMe() throws IOException {
        StringWriter writer = new StringWriter();
        Document document = new Document("123", "DOC-000001", "Testing", "A");
        Tester value = new Tester(document);
        new ObjectMapper().writeValue(writer, value);
        //assertThat(writer.toString(), is(""));
    }

    private final class Tester {
        private String className;
        private Object object;

        public Tester(Object object) {
            this.className = object.getClass().getName();
            this.object = object;
        }

        public String getClassName() {
            return className;
        }

        public Object getObject() {
            return object;
        }
    }
}
