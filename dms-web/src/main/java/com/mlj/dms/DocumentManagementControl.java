package com.mlj.dms;

import com.mlj.documents.DocumentRegistrationService;
import com.mlj.documents.RegisterDocumentCommand;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@RequestMapping("/dms/docs")
public class DocumentManagementControl {

    private final DocumentRegistrationService documentRegistrationService;

    public DocumentManagementControl(DocumentRegistrationService documentRegistrationService) {
        this.documentRegistrationService = documentRegistrationService;
    }

    @RequestMapping(method = GET)
    @ResponseBody
    public String list() {
        return "Hello World";
    }

    @RequestMapping(method = POST, consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public void register(@RequestBody RegisterDocumentCommand command) {
        System.out.println("command.getDocumentNo() = " + command.getDocumentNo());
        documentRegistrationService.registerDocument(command);
    }

}
