package com.romans91.commercialdocumentcollector.service;

import com.romans91.commercialdocumentcollector.model.CommercialDocument;
import com.romans91.commercialdocumentcollector.repository.CommercialDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommercialDocumentService {

    @Autowired
    private CommercialDocumentRepository commercialDocumentRepository;

    public Page<CommercialDocument> findAllSortedByCreatedAt() {

        Pageable sortByCreatedAt = PageRequest.of(0, (int)commercialDocumentRepository.count(), Sort.by("createdAt"));

        return commercialDocumentRepository.findAll(sortByCreatedAt);
    }
}
