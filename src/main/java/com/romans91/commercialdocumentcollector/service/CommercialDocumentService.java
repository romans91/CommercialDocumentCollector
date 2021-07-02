package com.romans91.commercialdocumentcollector.service;

import com.romans91.commercialdocumentcollector.model.CommercialDocument;
import com.romans91.commercialdocumentcollector.repository.CommercialDocumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class CommercialDocumentService {

    @Autowired
    private CommercialDocumentRepository commercialDocumentRepository;

    public Page<CommercialDocument> findPaginatedAndSorted(int page, int size) {

        return commercialDocumentRepository.findAll(PageRequest.of(page, size, Sort.by("createdAt")));
    }

    public Iterable<CommercialDocument> findAllSorted() {

        return commercialDocumentRepository.findAll(Sort.by("createdAt"));
    }
}
