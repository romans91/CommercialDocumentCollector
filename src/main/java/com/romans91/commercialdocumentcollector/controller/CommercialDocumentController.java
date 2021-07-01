package com.romans91.commercialdocumentcollector.controller;

import com.romans91.commercialdocumentcollector.model.CommercialDocument;
import com.romans91.commercialdocumentcollector.service.CommercialDocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/commercialdocuments")
public class CommercialDocumentController {

    @Autowired
    private CommercialDocumentService commercialDocumentService;

    @GetMapping
    List<? extends CommercialDocument> getAllCommercialDocuments() {
        return commercialDocumentService.findAllSortedByCreatedAt();
    }
}
