package com.romans91.commercialdocumentcollector.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "credit_notes")
public class CreditNote extends CommercialDocument {

    @JsonProperty
    @NotBlank(message = "Please provide a credit number.")
    private String creditNumber;

    public String getCreditNumber() {
        return this.creditNumber;
    }

    public CreditNote(String creditNumber, BigDecimal value) {
        this.creditNumber = creditNumber;
        this.value = value;
    }

    public CreditNote() {}
}