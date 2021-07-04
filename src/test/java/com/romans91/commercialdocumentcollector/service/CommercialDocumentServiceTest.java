package com.romans91.commercialdocumentcollector.service;

import com.romans91.commercialdocumentcollector.TestUtil;
import com.romans91.commercialdocumentcollector.model.CommercialDocument;
import com.romans91.commercialdocumentcollector.model.CreditNote;
import com.romans91.commercialdocumentcollector.model.Invoice;
import com.romans91.commercialdocumentcollector.repository.CommercialDocumentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.stream.StreamSupport;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class CommercialDocumentServiceTest {

    @Autowired
    private CommercialDocumentService commercialDocumentService;

    @Autowired
    private CommercialDocumentRepository commercialDocumentRepository;

    @AfterEach
    public void tearDown() {
        commercialDocumentRepository.deleteAll();
    }

    @Test
    public void testGetCommercialDocumentsCount() {
        commercialDocumentRepository.saveAll(TestUtil.getSampleCommercialDocuments());

        long commercialDocumentsCount =
                StreamSupport.stream(commercialDocumentService.findAllSorted().spliterator(), false).count();

        assertThat(commercialDocumentsCount, equalTo(commercialDocumentRepository.count()));
    }

    @Test
    public void testGetCommercialDocumentsId() {
        commercialDocumentRepository.saveAll(TestUtil.getSampleCommercialDocuments());

        for (CommercialDocument foundCommercialDocument : commercialDocumentService.findAllSorted()) {
            assertThat(commercialDocumentRepository.existsById(foundCommercialDocument.getId()), equalTo(true));
        }
    }

    @Test
    public void testGetCommercialDocumentsValue() {
        commercialDocumentRepository.saveAll(TestUtil.getSampleCommercialDocuments());

        for (CommercialDocument foundCommercialDocument : commercialDocumentService.findAllSorted()) {
            CommercialDocument existingCommercialDocument =
                    commercialDocumentRepository.getById(foundCommercialDocument.getId());

            assertThat(existingCommercialDocument.getValue(), equalTo(foundCommercialDocument.getValue()));
        }
    }

    @Test
    public void testGetCommercialDocumentsNumber() {
        commercialDocumentRepository.saveAll(TestUtil.getSampleCommercialDocuments());

        for (CommercialDocument foundCommercialDocument : commercialDocumentService.findAllSorted()) {
            CommercialDocument existingCommercialDocument =
                    commercialDocumentRepository.getById(foundCommercialDocument.getId());

            switch (existingCommercialDocument.getClass().getSimpleName()) {
                case "Invoice":
                    Invoice existingInvoice = (Invoice)existingCommercialDocument;
                    Invoice foundInvoice = (Invoice)foundCommercialDocument;

                    assertThat(foundInvoice.getInvoiceNumber(), equalTo(existingInvoice.getInvoiceNumber()));
                    break;
                case "CreditNote":
                    CreditNote existingCreditNote = (CreditNote)existingCommercialDocument;
                    CreditNote foundCreditNote = (CreditNote)foundCommercialDocument;

                    assertThat(foundCreditNote.getCreditNumber(), equalTo(existingCreditNote.getCreditNumber()));
                    break;
            }
        }
    }

    @Test
    public void testGetCommercialDocumentsCreatedAt() {
        commercialDocumentRepository.saveAll(TestUtil.getSampleCommercialDocuments());

        for (CommercialDocument foundCommercialDocument : commercialDocumentService.findAllSorted()) {
            CommercialDocument existingCommercialDocument =
                    commercialDocumentRepository.getById(foundCommercialDocument.getId());

            assertThat(existingCommercialDocument.getCreatedAt(), equalTo(foundCommercialDocument.getCreatedAt()));
        }
    }
}