package br.com.geosapiens.utils;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilBigDecimalTest {
    @Test
    void multiplyWithTwoValues() {
        BigDecimal result = UtilBigDecimal.multiply(BigDecimal.valueOf(2), BigDecimal.valueOf(3));
        assertEquals(BigDecimal.valueOf(6.00).setScale(2, RoundingMode.HALF_UP), result);
    }

    @Test
    void multiplyWithNullValues() {
        BigDecimal result = UtilBigDecimal.multiply(null, BigDecimal.valueOf(3));
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), result);
    }

    @Test
    void multiplyWithEmptyArray() {
        BigDecimal result = UtilBigDecimal.multiply();
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), result);
    }

    @Test
    void multiplyWithSingleValue() {
        BigDecimal result = UtilBigDecimal.multiply(BigDecimal.valueOf(5));
        assertEquals(BigDecimal.valueOf(5.00).setScale(2, RoundingMode.HALF_UP), result);
    }

    @Test
    void multiplyWithCustomScale() {
        BigDecimal result = UtilBigDecimal.multiply(3, BigDecimal.valueOf(2), BigDecimal.valueOf(3));
        assertEquals(BigDecimal.valueOf(6.000).setScale(3, RoundingMode.HALF_UP), result);
    }

    @Test
    void multiplyWithNullArray() {
        BigDecimal result = UtilBigDecimal.multiply((BigDecimal[]) null);
        assertEquals(BigDecimal.ZERO.setScale(2, RoundingMode.HALF_UP), result);
    }
}