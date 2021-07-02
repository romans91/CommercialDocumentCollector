package com.romans91.commercialdocumentcollector.repository;

import com.romans91.commercialdocumentcollector.model.CommercialDocument;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CommercialDocumentRepository extends JpaRepository<CommercialDocument, UUID> {
}
