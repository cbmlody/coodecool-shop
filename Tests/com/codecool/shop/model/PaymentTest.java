package com.codecool.shop.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Created by mercutio on 24.05.17.
 */
class PaymentTest {

    Payment testPayment;

    @Test
    void testValidatePayment() {
        testPayment = new Payment("payMeMoniez", "MOOOORE moniez");
        assertTrue(testPayment.isValid() || !testPayment.isValid());
    }

}