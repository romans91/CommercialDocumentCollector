package com.romans91.commercialdocumentcollector.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

@Entity
@Table(name = "credit_notes")
public class CreditNote extends CommercialDocument {

    @JsonProperty
    @NotBlank(message = "Please provide a credit number.")
    private String creditNumber;
}