package com.romans91.commercialdocumentcollector.controller;

import com.romans91.commercialdocumentcollector.model.CommercialDocument;
import com.romans91.commercialdocumentcollector.service.CommercialDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/commercialdocuments")
public class CommercialDocumentController {

    @Autowired
    private CommercialDocumentService commercialDocumentService;

    @GetMapping
    Page<CommercialDocument> getAllCommercialDocuments() {
        return commercialDocumentService.findAllSortedByCreatedAt();
    }
}
