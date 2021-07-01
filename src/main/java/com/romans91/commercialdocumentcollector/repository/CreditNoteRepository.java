package com.romans91.commercialdocumentcollector.repository;

import com.romans91.commercialdocumentcollector.model.CreditNote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CreditNoteRepository extends JpaRepository<CreditNote, UUID> {
}
