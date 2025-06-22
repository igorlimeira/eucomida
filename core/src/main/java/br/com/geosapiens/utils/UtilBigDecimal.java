package br.com.geosapiens.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class UtilBigDecimal {

    private static final int SCALE_OF_TWO = 2;

    public static BigDecimal multiply(BigDecimal... values) {
        return multiplyWithScale(SCALE_OF_TWO, values);
    }

    public static BigDecimal multiply(int scale, BigDecimal... values) {
        return multiplyWithScale(scale, values);
    }

    private static BigDecimal multiplyWithScale(int scale, BigDecimal... values) {
        if (values == null || values.length == 0) {
            return BigDecimal.ZERO.setScale(scale, RoundingMode.HALF_UP);
        }

        BigDecimal result = values[0];
        if (result == null) {
            return BigDecimal.ZERO.setScale(scale, RoundingMode.HALF_UP);
        }

        for (int i = 1; i < values.length; i++) {
            if (values[i] == null) {
                return BigDecimal.ZERO;
            }
            result = result.multiply(values[i]);
        }

        return result.setScale(scale, RoundingMode.HALF_UP);
    }
}
