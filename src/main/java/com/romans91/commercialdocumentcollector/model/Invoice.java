package com.romans91.commercialdocumentcollector.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Entity
@Table(name = "invoices")
public class Invoice extends CommercialDocument {

    @JsonProperty
    @NotBlank(message = "Please provide an invoice number.")
    private String invoiceNumber;

    public String getInvoiceNumber() {
        return this.invoiceNumber;
    }

    public Invoice(String invoiceNumber, BigDecimal value) {
        this.invoiceNumber = invoiceNumber;
        this.value = value;
    }

    public Invoice() {}
}
