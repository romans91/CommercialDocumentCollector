package com.romans91.commercialdocumentcollector.service;

import com.romans91.commercialdocumentcollector.model.Invoice;
import com.romans91.commercialdocumentcollector.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public List<Invoice> saveInvoices(List<Invoice> invoices) {
        return invoiceRepository.saveAll(invoices);
    }

    public List<Invoice> findAll() {
        return invoiceRepository.findAll();
    }
}
