package com.romans91.commercialdocumentcollector.controller;

import com.romans91.commercialdocumentcollector.TestUtil;
import com.romans91.commercialdocumentcollector.model.Invoice;
import com.romans91.commercialdocumentcollector.repository.InvoiceRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import javax.json.JsonArray;
import javax.json.JsonValue;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class InvoiceControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private InvoiceRepository repository;

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testPostInvoicesResponseCount() throws Exception {
        JsonArray invoices = TestUtil.getSampleInvoicesJson();

        mvc.perform(post("/api/v1/invoices")
                .content(invoices.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        hasSize(invoices.size())));
    }

    @Test
    public void testPostInvoicesResponseId() throws Exception {
        JsonArray invoices = TestUtil.getSampleInvoicesJson();

        ResultActions actions = mvc.perform(post("/api/v1/invoices")
                .content(invoices.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (JsonValue invoice: invoices) {
            actions.andExpect(jsonPath("$[" + index++ + "].id",
                    matchesPattern(TestUtil.UUID_PATTERN)));
        }
    }

    @Test
    public void testPostInvoicesConstraintViolation() throws Exception {
        JsonArray invalidInvoiceBatches = TestUtil.getSampleInvalidInvoicesJson();

        for (int i = 0; i < invalidInvoiceBatches.size(); i++) {
            JsonArray invalidInvoiceBatch = invalidInvoiceBatches.getJsonArray(i);

            mvc.perform(post("/api/v1/invoices")
                    .content(invalidInvoiceBatch.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
    }

    @Test
    public void testPostInvoicesResponseValue() throws Exception {
        JsonArray invoices = TestUtil.getSampleInvoicesJson();

        ResultActions actions = mvc.perform(post("/api/v1/invoices")
                .content(invoices.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (JsonValue invoice: invoices) {
            actions.andExpect(jsonPath("$[" + index++ + "].value",
                    comparesEqualTo(Double.valueOf(invoice.asJsonObject().get("value").toString()))));
        }
    }

    @Test
    public void testPostInvoicesResponseInvoiceNumber() throws Exception {
        JsonArray invoices = TestUtil.getSampleInvoicesJson();

        ResultActions actions = mvc.perform(post("/api/v1/invoices")
                .content(invoices.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (JsonValue invoice: invoices) {
            actions.andExpect(jsonPath("$[" + index++ + "].invoiceNumber",
                    is(invoice.asJsonObject().getString("invoiceNumber"))));
        }
    }

    @Test
    public void testPostInvoicesResponseCreatedAt() throws Exception {
        JsonArray invoices = TestUtil.getSampleInvoicesJson();

        ResultActions actions = mvc.perform(post("/api/v1/invoices")
                .content(invoices.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (JsonValue invoice: invoices) {
            actions.andExpect(jsonPath("$[" + index++ + "].createdAt",
                    startsWith(TestUtil.getCurrentTimeMillis(5))));
        }
    }

    @Test
    public void testPostInvoicesRepositoryCount() throws Exception {
        JsonArray invoices = TestUtil.getSampleInvoicesJson();

        mvc.perform(post("/api/v1/invoices")
                .content(invoices.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        hasSize((int)repository.count())));
    }

    @Test
    public void testPostInvoicesRepositoryId() throws Exception {
        JsonArray invoices = TestUtil.getSampleInvoicesJson();

        ResultActions actions = mvc.perform(post("/api/v1/invoices")
                .content(invoices.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (Invoice invoice: repository.findAll()) {
            actions.andExpect(jsonPath("$["+ index++ +"].id", is(invoice.getId().toString())));
        }
    }

    @Test
    public void testPostInvoicesRepositoryValue() throws Exception {
        JsonArray invoices = TestUtil.getSampleInvoicesJson();

        ResultActions actions = mvc.perform(post("/api/v1/invoices")
                .content(invoices.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (Invoice invoice: repository.findAll()) {
            actions.andExpect(jsonPath("$["+ index++ +"].value", equalTo(invoice.getValue())));
        }
    }

    @Test
    public void testPostInvoicesRepositoryInvoiceNumber() throws Exception {
        JsonArray invoices = TestUtil.getSampleInvoicesJson();

        ResultActions actions = mvc.perform(post("/api/v1/invoices")
                .content(invoices.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (Invoice invoice: repository.findAll()) {
            actions.andExpect(jsonPath("$["+ index++ +"].invoiceNumber", is(invoice.getInvoiceNumber())));
        }
    }

    @Test
    public void testPostInvoicesRepositoryCreatedAt() throws Exception {
        JsonArray invoices = TestUtil.getSampleInvoicesJson();

        ResultActions actions = mvc.perform(post("/api/v1/invoices")
                .content(invoices.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (Invoice invoice: repository.findAll()) {
            actions.andExpect(jsonPath("$["+ index++ +"].createdAt", is(invoice.getCreatedAt())));
        }
    }
}
