package dev.spozap.evfishingcore.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;

public class NumberUtils {

    public static double roundDouble(double value) {
        return BigDecimal.valueOf(value).setScale(2, RoundingMode.DOWN).doubleValue();
    }

}
