package com.romans91.commercialdocumentcollector.controller;

import com.romans91.commercialdocumentcollector.TestUtil;
import com.romans91.commercialdocumentcollector.model.CommercialDocument;
import com.romans91.commercialdocumentcollector.model.CreditNote;
import com.romans91.commercialdocumentcollector.model.Invoice;
import com.romans91.commercialdocumentcollector.repository.CommercialDocumentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class CommercialDocumentControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CommercialDocumentRepository repository;

    @BeforeEach
    public void setUp() {
        repository.saveAll(TestUtil.getSampleCommercialDocuments());
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testGetCommercialDocumentsCount() throws Exception {
        mvc.perform(get("/api/v1/commercialdocuments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(8)));
    }

    @Test
    public void testGetCommercialDocumentsID() throws Exception {
        ResultActions actions = mvc.perform(get("/api/v1/commercialdocuments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (CommercialDocument commercialDocument: repository.findAll(Sort.by("createdAt"))) {
            actions.andExpect(jsonPath("$[" + index++ + "].id", is(commercialDocument.getId().toString())));
        }
    }

    @Test
    public void testGetCommercialDocumentsValue() throws Exception {
        ResultActions actions = mvc.perform(get("/api/v1/commercialdocuments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (CommercialDocument commercialDocument: repository.findAll(Sort.by("createdAt"))) {
            actions.andExpect(jsonPath("$[" + index++ + "].value", is(commercialDocument.getValue())));
        }
    }

    @Test
    public void testGetCommercialDocumentsNumber() throws Exception {
        ResultActions actions = mvc.perform(get("/api/v1/commercialdocuments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (CommercialDocument commercialDocument: repository.findAll(Sort.by("createdAt"))) {
            switch (commercialDocument.getClass().getSimpleName()) {
                case "Invoice":
                    actions.andExpect(jsonPath("$[" + index++ + "].invoiceNumber",
                            is(((Invoice)commercialDocument).getInvoiceNumber())));
                    break;
                case "CreditNote":
                    actions.andExpect(jsonPath("$[" + index++ + "].creditNumber",
                            is(((CreditNote)commercialDocument).getCreditNumber())));
                    break;
            }
        }
    }

    @Test
    public void testGetCommercialDocumentsCreatedAt() throws Exception {
        ResultActions actions = mvc.perform(get("/api/v1/commercialdocuments")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (CommercialDocument commercialDocument: repository.findAll(Sort.by("createdAt"))) {
            actions.andExpect(jsonPath("$[" + index++ + "].createdAt", is(commercialDocument.getCreatedAt())));
        }
    }

    @Test
    public void testGetCommercialDocumentsPaginationCount() throws Exception {
        mvc.perform(get("/api/v1/commercialdocuments?page=0&size=4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("content", hasSize(4)));

        mvc.perform(get("/api/v1/commercialdocuments?page=1&size=4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("content", hasSize(4)));
    }

    @Test
    public void testGetCommercialDocumentsPaginationOutOfBoundsCount() throws Exception {
        mvc.perform(get("/api/v1/commercialdocuments?page=2&size=4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("content", hasSize(0)));
    }

    @Test
    public void testGetCommercialDocumentsPaginationID() throws Exception {
        ResultActions actions = mvc.perform(get("/api/v1/commercialdocuments?page=0&size=4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (CommercialDocument commercialDocument: repository.findAll(PageRequest.of(0, 4, Sort.by("createdAt")))) {
            actions.andExpect(jsonPath("$.content[" + index++ + "].id",
                    is(commercialDocument.getId().toString())));
        }
    }

    @Test
    public void testGetCommercialDocumentsPaginationValue() throws Exception {
        ResultActions actions = mvc.perform(get("/api/v1/commercialdocuments?page=0&size=4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (CommercialDocument commercialDocument: repository.findAll(PageRequest.of(0, 4, Sort.by("createdAt")))) {
            actions.andExpect(jsonPath("$.content[" + index++ + "].value", is(commercialDocument.getValue())));
        }
    }

    @Test
    public void testGetCommercialDocumentsPaginationNumber() throws Exception {
        ResultActions actions = mvc.perform(get("/api/v1/commercialdocuments?page=0&size=4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (CommercialDocument commercialDocument: repository.findAll(PageRequest.of(0, 4, Sort.by("createdAt")))) {
            switch (commercialDocument.getClass().getSimpleName()) {
                case "Invoice":
                    actions.andExpect(jsonPath("$.content[" + index++ + "].invoiceNumber",
                            is(((Invoice)commercialDocument).getInvoiceNumber())));
                    break;
                case "CreditNote":
                    actions.andExpect(jsonPath("$.content[" + index++ + "].creditNumber",
                            is(((CreditNote)commercialDocument).getCreditNumber())));
                    break;
            }
        }
    }

    @Test
    public void testGetCommercialDocumentsPaginationCreatedAt() throws Exception {
        ResultActions actions = mvc.perform(get("/api/v1/commercialdocuments?page=0&size=4")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (CommercialDocument commercialDocument: repository.findAll(PageRequest.of(0, 4, Sort.by("createdAt")))) {
            actions.andExpect(jsonPath("$.content[" + index++ + "].createdAt", is(commercialDocument.getCreatedAt())));
        }
    }
}
