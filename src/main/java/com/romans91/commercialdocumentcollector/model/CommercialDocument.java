package com.romans91.commercialdocumentcollector.model;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class CommercialDocument {

    @Id
    @GeneratedValue(generator = "UUID")
    @Column(updatable = false, nullable = false, columnDefinition = "BINARY(16)")
    private UUID id;

    @CreationTimestamp
    @Column(updatable = false, nullable = false, columnDefinition = "DATETIME(6)")
    private Timestamp createdAt;

    @Digits(integer=6, fraction=2, message = "Please enter a decimal value with up to 2 fractional digits.")
    @DecimalMin(value = "0.01", message = "Please enter a value of at least $0.01.")
    protected BigDecimal value;

    @JsonGetter("createdAt")
    public String getCreatedAt() {
        return String.valueOf(createdAt.getTime());
    }

    @JsonGetter("id")
    public UUID getId() {
        return id;
    }

    @JsonGetter("value")
    public double getValue() {
        return value.doubleValue();
    }
}
