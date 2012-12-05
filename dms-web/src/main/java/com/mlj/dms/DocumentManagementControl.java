package com.mlj.dms;

import com.mlj.documents.DocumentRegistrationService;
import com.mlj.documents.RegisterDocumentCommand;
import com.mlj.views.DocumentDto;
import com.mlj.views.DocumentListView;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/dms/docs")
public class DocumentManagementControl {

    private final DocumentRegistrationService documentRegistrationService;
    private final DocumentListView documentListView;

    public DocumentManagementControl(DocumentRegistrationService documentRegistrationService,
                                     DocumentListView documentListView) {
        this.documentRegistrationService = documentRegistrationService;
        this.documentListView = documentListView;
    }

    @RequestMapping(method = GET, produces = "application/json")
    @ResponseBody
    public List<DocumentDto> list() {
        return documentListView.list();
    }

    @RequestMapping(method = POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterDocumentCommand command) {
        System.out.println("command.getDocumentNo() = " + command.getDocumentNo());
        documentRegistrationService.registerDocument(command);
    }

}
