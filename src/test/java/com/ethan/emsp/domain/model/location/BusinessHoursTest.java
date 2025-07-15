package com.ethan.emsp.domain.model.location;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusinessHoursTest {

    @Test
    void of() {
        assertEquals("(08:00-12:00)", BusinessHours.of("08:00", "12:00").toString());
    }

    @Test
    void testToString() {
        assertEquals("(08:00-12:00)", BusinessHours.of("08:00", "12:00").toString());
    }
}