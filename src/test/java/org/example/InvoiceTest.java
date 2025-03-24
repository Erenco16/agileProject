package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.Field;

class InvoiceTest {

    @Test
    void testValidateOrderIdAlwaysReturnsTrue() {
        Invoice invoice = new Invoice();
        assertTrue(invoice.validateOrderId());
    }

    @Test
    void testValidateTotalPayableAmountDueAlwaysReturnsTrue() {
        Invoice invoice = new Invoice();
        assertTrue(invoice.validateTotalPayableAmountDue());
    }

    @Test
    void testSetAndGetTotalPayableAmountDue() {
        Invoice invoice = new Invoice();
        invoice.setTotal_payable_amount_due("150.50");
        assertEquals("150.50", invoice.getTotal_payable_amount_due());
    }
}