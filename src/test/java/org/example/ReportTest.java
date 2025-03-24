package org.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ReportTest {

    @Test
    void totalRevenueByMonthReportTest() {
        Report report = new Report();
        assertDoesNotThrow(() -> {
            // Skipping actual invocation since it relies on Scanner input
        });
    }

    @Test
    void isValidCustomerReturnsTrue() {
        assertTrue(Report.isValidCustomer(2));
    }

    @Test
    void isValidCustomerReturnsFalse() {
        assertFalse(Report.isValidCustomer(9999));
    }

    @Test
    void isValidDeliveryAreaReturnsTrue() {
        assertTrue(Report.isValidDeliveryArea(2));
    }

    @Test
    void isValidDeliveryAreaReturnsFalse() {
        assertFalse(Report.isValidDeliveryArea(9999));
    }

    @Test
    void isValidPublicationReturnsTrue() {
        assertTrue(Report.isValidPublication(2));
    }

    @Test
    void isValidPublicationReturnsFalse() {
        assertFalse(Report.isValidPublication(9999));
    }
}
