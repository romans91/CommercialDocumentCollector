package com.romans91.commercialdocumentcollector.controller;

import com.romans91.commercialdocumentcollector.TestUtil;
import com.romans91.commercialdocumentcollector.model.CreditNote;
import com.romans91.commercialdocumentcollector.repository.CreditNoteRepository;
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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-integration-test.properties")
public class CreditNoteControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CreditNoteRepository repository;

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testPostCreditNotesResponseCount() throws Exception {
        JsonArray creditNotes = TestUtil.getSampleCreditNotesJson();

        mvc.perform(post("/api/v1/creditnotes" +
                "")
                .content(creditNotes.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        hasSize(creditNotes.size())));
    }

    @Test
    public void testPostCreditNotesResponseId() throws Exception {
        JsonArray creditNotes = TestUtil.getSampleCreditNotesJson();

        ResultActions actions = mvc.perform(post("/api/v1/creditnotes" +
                "")
                .content(creditNotes.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (JsonValue creditNote: creditNotes) {
            actions.andExpect(jsonPath("$[" + index++ + "].id",
                    matchesPattern(TestUtil.UUID_PATTERN)));
        }
    }

    @Test
    public void testPostCreditNotesValidationFailure() throws Exception {
        JsonArray invalidCreditNoteBatches = TestUtil.getSampleInvalidCreditNotesJson();

        for (int i = 0; i < invalidCreditNoteBatches.size(); i++) {
            JsonArray invalidCreditNoteBatch = invalidCreditNoteBatches.getJsonArray(i);

            mvc.perform(post("/api/v1/creditnotes")
                    .content(invalidCreditNoteBatch.toString())
                    .contentType(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
    }

    @Test
    public void testPostCreditNotesResponseValue() throws Exception {
        JsonArray creditNotes = TestUtil.getSampleCreditNotesJson();

        ResultActions actions = mvc.perform(post("/api/v1/creditnotes" +
                "")
                .content(creditNotes.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (JsonValue creditNote: creditNotes) {
            actions.andExpect(jsonPath("$[" + index++ + "].value",
                    comparesEqualTo(Double.valueOf(creditNote.asJsonObject().get("value").toString()))));
        }
    }

    @Test
    public void testPostCreditNotesResponseCreditNumber() throws Exception {
        JsonArray creditNotes = TestUtil.getSampleCreditNotesJson();

        ResultActions actions = mvc.perform(post("/api/v1/creditnotes" +
                "")
                .content(creditNotes.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (JsonValue creditNote: creditNotes) {
            actions.andExpect(jsonPath("$[" + index++ + "].creditNumber",
                    is(creditNote.asJsonObject().getString("creditNumber"))));
        }
    }

    @Test
    public void testPostCreditNotesResponseCreatedAt() throws Exception {
        JsonArray creditNotes = TestUtil.getSampleCreditNotesJson();

        ResultActions actions = mvc.perform(post("/api/v1/creditnotes" +
                "")
                .content(creditNotes.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (JsonValue creditNote: creditNotes) {
            actions.andExpect(jsonPath("$[" + index++ + "].createdAt",
                    startsWith(TestUtil.getCurrentTimeMillis(5))));
        }
    }

    @Test
    public void testPostCreditNotesRepositoryCount() throws Exception {
        JsonArray creditNotes = TestUtil.getSampleCreditNotesJson();

        mvc.perform(post("/api/v1/creditnotes" +
                "")
                .content(creditNotes.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",
                        hasSize((int)repository.count())));
    }

    @Test
    public void testPostCreditNotesRepositoryId() throws Exception {
        JsonArray creditNotes = TestUtil.getSampleCreditNotesJson();

        ResultActions actions = mvc.perform(post("/api/v1/creditnotes" +
                "")
                .content(creditNotes.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (CreditNote creditNote: repository.findAll()) {
            actions.andExpect(jsonPath("$[" + index++ + "].id", is(creditNote.getId().toString())));
        }
    }

    @Test
    public void testPostCreditNotesRepositoryValue() throws Exception {
        JsonArray creditNotes = TestUtil.getSampleCreditNotesJson();

        ResultActions actions = mvc.perform(post("/api/v1/creditnotes" +
                "")
                .content(creditNotes.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (CreditNote creditNote: repository.findAll()) {
            actions.andExpect(jsonPath("$[" + index++ + "].value", equalTo(creditNote.getValue())));
        }
    }

    @Test
    public void testPostCreditNotesRepositoryCreditNumber() throws Exception {
        JsonArray creditNotes = TestUtil.getSampleCreditNotesJson();

        ResultActions actions = mvc.perform(post("/api/v1/creditnotes" +
                "")
                .content(creditNotes.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (CreditNote creditNote: repository.findAll()) {
            actions.andExpect(jsonPath("$[" + index++ + "].creditNumber", is(creditNote.getCreditNumber())));
        }
    }

    @Test
    public void testPostCreditNotesRepositoryCreatedAt() throws Exception {
        JsonArray creditNotes = TestUtil.getSampleCreditNotesJson();

        ResultActions actions = mvc.perform(post("/api/v1/creditnotes" +
                "")
                .content(creditNotes.toString())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        int index = 0;
        for (CreditNote creditNote: repository.findAll()) {
            actions.andExpect(jsonPath("$[" + index++ + "].createdAt", is(creditNote.getCreatedAt())));
        }
    }
}