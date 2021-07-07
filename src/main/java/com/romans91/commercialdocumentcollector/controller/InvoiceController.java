package com.romans91.commercialdocumentcollector.controller;

import com.romans91.commercialdocumentcollector.model.Invoice;
import com.romans91.commercialdocumentcollector.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Validated
@RestController
@RequestMapping("api/v1/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    List<Invoice> importBatch(@RequestBody @NotEmpty(message = "Please send a batch of invoices with at least one.")
                                      List<@Valid Invoice> newInvoices) {
        return invoiceService.saveInvoices(newInvoices);
    }
}
