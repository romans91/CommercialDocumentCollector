package com.romans91.commercialdocumentcollector.service;

import com.romans91.commercialdocumentcollector.TestUtil;
import com.romans91.commercialdocumentcollector.model.Invoice;
import com.romans91.commercialdocumentcollector.repository.InvoiceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import java.util.List;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class InvoiceServiceTest {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @AfterEach
    public void tearDown() {
        invoiceRepository.deleteAll();
    }

    @Test
    public void testInsertInvoicesCount() {
        List<Invoice> invoices = TestUtil.getSampleInvoices();

        invoiceService.saveInvoices(invoices);

        assertThat(invoiceRepository.count(), equalTo(invoices.stream().count()));
    }

    @Test
    public void testInsertInvoicesId() {
        List<Invoice> invoices = TestUtil.getSampleInvoices();

        invoiceService.saveInvoices(invoices);

        int index = 0;
        for (Invoice invoice: invoiceRepository.findAll())
            assertThat(invoice.getId(), equalTo(invoices.get(index++).getId()));
    }

    @Test
    public void testInsertInvoicesValue() {
        List<Invoice> invoices = TestUtil.getSampleInvoices();

        invoiceService.saveInvoices(invoices);

        int index = 0;
        for (Invoice invoice: invoiceRepository.findAll())
            assertThat(invoice.getValue(), equalTo(invoices.get(index++).getValue()));
    }

    @Test
    public void testInsertInvoicesNumber() {
        List<Invoice> invoices = TestUtil.getSampleInvoices();

        invoiceService.saveInvoices(invoices);

        int index = 0;
        for (Invoice invoice: invoiceRepository.findAll())
            assertThat(invoice.getInvoiceNumber(), equalTo(invoices.get(index++).getInvoiceNumber()));
    }

    @Test
    public void testInsertInvoicesCreatedAt() {
        List<Invoice> invoices = TestUtil.getSampleInvoices();

        invoiceService.saveInvoices(invoices);

        int index = 0;
        for (Invoice invoice: invoiceRepository.findAll())
            assertThat(invoice.getCreatedAt(), equalTo(invoices.get(index++).getCreatedAt()));
    }
}