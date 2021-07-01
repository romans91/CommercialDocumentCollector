package com.romans91.commercialdocumentcollector.controller;

import com.romans91.commercialdocumentcollector.model.CreditNote;
import com.romans91.commercialdocumentcollector.service.CreditNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@RestController
@RequestMapping("api/v1/creditnotes")
public class CreditNoteController {

    @Autowired
    private CreditNoteService creditNoteService;

    @PostMapping()
    List<CreditNote> importBatch(@RequestBody @NotEmpty(message = "Please send a batch of credit notes with at least one.") List<@Valid CreditNote> newCreditNotes) {
        return creditNoteService.saveCreditNotes(newCreditNotes);
    }
}
