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

    @GetMapping()
    Iterable<CommercialDocument> getCommercialDocumentsAllSorted() {

        return commercialDocumentService.findAllSorted();
    }

    @GetMapping(params = { "page", "size" })
    Page<CommercialDocument> getCommercialDocumentsPaginatedSorted(@RequestParam("page") int page,
                                                              @RequestParam("size") int size) {

        return commercialDocumentService.findPaginatedAndSorted(page, size);
    }
}
