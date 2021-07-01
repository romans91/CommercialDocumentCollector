package com.romans91.commercialdocumentcollector.service;

import com.romans91.commercialdocumentcollector.model.CreditNote;
import com.romans91.commercialdocumentcollector.repository.CreditNoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CreditNoteService {

    @Autowired
    private CreditNoteRepository creditNoteRepository;

    public List<CreditNote> saveCreditNotes(List<CreditNote> creditNotes) {
        return creditNoteRepository.saveAll(creditNotes);
    }

    public List<CreditNote> findAll() {
        return creditNoteRepository.findAll();
    }
}
