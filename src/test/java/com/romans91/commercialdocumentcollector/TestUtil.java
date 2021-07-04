package com.romans91.commercialdocumentcollector;

import com.romans91.commercialdocumentcollector.model.CommercialDocument;
import com.romans91.commercialdocumentcollector.model.CreditNote;
import com.romans91.commercialdocumentcollector.model.Invoice;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonBuilderFactory;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class TestUtil {
    private static final JsonBuilderFactory JSON_BUILDER_FACTORY =
            Json.createBuilderFactory(null);
    public static final Pattern UUID_PATTERN =
            Pattern.compile("[a-f0-9]{8}-[a-f0-9]{4}-4[a-f0-9]{3}-[89aAbB][a-f0-9]{3}-[a-f0-9]{12}");

    public static String getCurrentTimeMillis(int digitsToTruncate) {

        return String.valueOf(System.currentTimeMillis()).substring(0, digitsToTruncate);
    }

    public static List<CreditNote> getSampleCreditNotes() {
        return Arrays.asList(
                        new CreditNote("CRD-0000", new BigDecimal(1200)),
                        new CreditNote("CRD-0001", new BigDecimal(1500)),
                        new CreditNote("CRD-0002", new BigDecimal(1100)),
                        new CreditNote("CRD-0003", new BigDecimal(1700))
        );
    }

    public static JsonArray getSampleCreditNotesJson() {
        List<CreditNote> creditNotes = getSampleCreditNotes();

        JsonArrayBuilder arrayBuilder = JSON_BUILDER_FACTORY.createArrayBuilder();

        for (CreditNote creditNote : creditNotes) {
            arrayBuilder.add(JSON_BUILDER_FACTORY.createObjectBuilder()
                    .add("creditNumber", creditNote.getCreditNumber())
                    .add("value", creditNote.getValue()));
        }

        return arrayBuilder.build();
    }

    public static JsonArray getSampleInvalidCreditNotesJson() {
        return JSON_BUILDER_FACTORY.createArrayBuilder()
                .add(JSON_BUILDER_FACTORY.createArrayBuilder()
                        .add(JSON_BUILDER_FACTORY.createObjectBuilder()
                                .add("creditNumber", "CRD-0000")
                                .add("value", "2500.001")))
                .add(JSON_BUILDER_FACTORY.createArrayBuilder()
                        .add(JSON_BUILDER_FACTORY.createObjectBuilder()
                                .add("creditNumber", "CRD-0001")
                                .add("value", "25000000.00"))).build();
    }

    public static List<Invoice> getSampleInvoices() {
        return Arrays.asList(
                        new Invoice("INV-0000", new BigDecimal(2500)),
                        new Invoice("INV-0001", new BigDecimal(2000)),
                        new Invoice("INV-0002", new BigDecimal(1600)),
                        new Invoice("INV-0003", new BigDecimal(2100))
                );
    }

    public static JsonArray getSampleInvoicesJson() {
        List<Invoice> invoices = getSampleInvoices();

        JsonArrayBuilder arrayBuilder = JSON_BUILDER_FACTORY.createArrayBuilder();

        for (Invoice invoice : invoices) {
            arrayBuilder.add(JSON_BUILDER_FACTORY.createObjectBuilder()
                    .add("invoiceNumber", invoice.getInvoiceNumber())
                    .add("value", invoice.getValue()));
        }

        return arrayBuilder.build();
    }

    public static JsonArray getSampleInvalidInvoicesJson() {

        return JSON_BUILDER_FACTORY.createArrayBuilder()
                .add(JSON_BUILDER_FACTORY.createArrayBuilder()
                        .add(JSON_BUILDER_FACTORY.createObjectBuilder()
                                .add("invoiceNumber", "INV-0000")
                                .add("value", "2500.001")))
                .add(JSON_BUILDER_FACTORY.createArrayBuilder()
                        .add(JSON_BUILDER_FACTORY.createObjectBuilder()
                                .add("invoiceNumber", "INV-0001")
                                .add("value", "25000000.00")))
                .add(JSON_BUILDER_FACTORY.createArrayBuilder()
                        .add(JSON_BUILDER_FACTORY.createObjectBuilder()
                                .add("invoiceNumber", "INV-0002")
                                .add("value", "Text in a numeric input field."))).build();
    }

    public static List<CommercialDocument> getSampleCommercialDocuments() {
        return Arrays.asList(
                        new Invoice("DOC-0000", new BigDecimal(2500)),
                        new Invoice("DOC-0001", new BigDecimal(2000)),
                        new CreditNote("DOC-0002", new BigDecimal(1200)),
                        new CreditNote("DOC-0003", new BigDecimal(1500)),
                        new Invoice("DOC-0004", new BigDecimal(1600)),
                        new Invoice("DOC-0005", new BigDecimal(2100)),
                        new CreditNote("DOC-0006", new BigDecimal(1100)),
                        new CreditNote("DOC-0007", new BigDecimal(1700))
                );
    }
}
