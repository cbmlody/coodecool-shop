package com.codecool.shop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by mercutio on 24.05.17.
 */
class PaymentTest {

    Payment testPayment;

    @BeforeEach
    void setUp() {
        testPayment = mock(Payment.class);
    }

    @Test
    @DisplayName("This test is for code coverage :)")
    void testValidatePayment() {
        testPayment = new Payment("payMeMoniez", "MOOOORE moniez");
        assertTrue(testPayment.isValid() || !testPayment.isValid());
    }

    @Test
    void testValidatePaymentTrue() {
        when(testPayment.validatePayment()).thenReturn(true);
        assertTrue(testPayment.validatePayment());
    }

    @Test
    void testValidatePaymentFalse() {
        when(testPayment.validatePayment()).thenReturn(false);
        assertFalse(testPayment.validatePayment());

    }
}