package com.romans91.commercialdocumentcollector.service;

import com.romans91.commercialdocumentcollector.model.CommercialDocument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class CommercialDocumentService {

    @Autowired
    private CreditNoteService creditNoteService;

    @Autowired
    private InvoiceService invoiceService;

    public List<? extends CommercialDocument> findAllSortedByCreatedAt() {

        List<? extends CommercialDocument> commercialDocuments =
                Stream.concat(creditNoteService.findAll().stream(), invoiceService.findAll().stream())
                        .collect(Collectors.toList());

        Collections.sort(commercialDocuments);
        return commercialDocuments;
    }
}
