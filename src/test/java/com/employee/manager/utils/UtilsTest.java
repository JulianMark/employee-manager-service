package com.employee.manager.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.employee.manager.utils.Utils.validateIdNumber;
import static com.employee.manager.utils.Utils.validateNotNullOrEmpty;
import static com.employee.manager.utils.Utils.validateRequest;

@DisplayName("Method utils test")
class UtilsTest {

    @Test
    @DisplayName("When validateRequest receives request with null value")
    void validateRequest_RequestIsNull() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> validateRequest(null));
    }

    @Test
    @DisplayName("When validateNotNullOrEmpty receives Empty String or String with null value")
    void validateNotNullOrEmpty_StringIsNullOrEmpty() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> validateNotNullOrEmpty(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> validateNotNullOrEmpty(""));
    }

    @Test
    @DisplayName("When validateIdNumber receives Integer less than zero or Integer with null value")
    void validateIdNumber_IntegerIsNullOrLessThanZero() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> validateIdNumber(null));
        Assertions.assertThrows(IllegalArgumentException.class, () -> validateIdNumber(0));
    }
}