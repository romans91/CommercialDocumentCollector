package com.romans91.commercialdocumentcollector.service;

import com.romans91.commercialdocumentcollector.TestUtil;
import com.romans91.commercialdocumentcollector.model.CreditNote;
import com.romans91.commercialdocumentcollector.repository.CreditNoteRepository;
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
public class CreditNoteServiceTest {

    @Autowired
    private CreditNoteService creditNoteService;

    @Autowired
    private CreditNoteRepository creditNoteRepository;

    @AfterEach
    public void tearDown() {
        creditNoteRepository.deleteAll();
    }

    @Test
    public void testInsertCreditNotesCount() {
        List<CreditNote> creditNotes = TestUtil.getSampleCreditNotes();

        creditNoteService.saveCreditNotes(creditNotes);

        assertThat(creditNoteRepository.count(), equalTo(creditNotes.stream().count()));
    }

    @Test
    public void testInsertCreditNotesId() {
        List<CreditNote> creditNotes = TestUtil.getSampleCreditNotes();

        creditNoteService.saveCreditNotes(creditNotes);

        int index = 0;
        for (CreditNote creditNote: creditNoteRepository.findAll())
            assertThat(creditNote.getId(), equalTo(creditNotes.get(index++).getId()));
    }

    @Test
    public void testInsertCreditNotesValue() {
        List<CreditNote> creditNotes = TestUtil.getSampleCreditNotes();

        creditNoteService.saveCreditNotes(creditNotes);

        int index = 0;
        for (CreditNote creditNote: creditNoteRepository.findAll())
            assertThat(creditNote.getValue(), equalTo(creditNotes.get(index++).getValue()));
    }

    @Test
    public void testInsertCreditNotesNumber() {
        List<CreditNote> creditNotes = TestUtil.getSampleCreditNotes();

        creditNoteService.saveCreditNotes(creditNotes);

        int index = 0;
        for (CreditNote creditNote: creditNoteRepository.findAll())
            assertThat(creditNote.getCreditNumber(), equalTo(creditNotes.get(index++).getCreditNumber()));
    }

    @Test
    public void testInsertCreditNotesCreatedAt() {
        List<CreditNote> creditNotes = TestUtil.getSampleCreditNotes();

        creditNoteService.saveCreditNotes(creditNotes);

        int index = 0;
        for (CreditNote creditNote: creditNoteRepository.findAll())
            assertThat(creditNote.getCreatedAt(), equalTo(creditNotes.get(index++).getCreatedAt()));
    }
}
