package ninja.afon.util;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

public class NumberUtil {

    public static final int DEFAULT_SCALE = 15;

    public static final int OUTPUT_SCALE = 10;

    protected static final int DEFAULT_PRECISION = 34;

    /**
     * RoundingMode.HALF_EVEN
     */
    public static final RoundingMode DEFAULT_ROUNDING_MODE = RoundingMode.FLOOR;

    public static final MathContext DEFAULT_MATH_CONTEXT =
            new MathContext(DEFAULT_PRECISION, DEFAULT_ROUNDING_MODE);

    private NumberUtil() {
    }

    public static BigDecimal parseNumber(String s) {
        BigDecimal number = new BigDecimal(s, DEFAULT_MATH_CONTEXT);
        return checkScale(number);
    }

    public static BigDecimal checkScale(BigDecimal result) {
        return checkScale(result, DEFAULT_SCALE);
    }

    public static BigDecimal checkScale(BigDecimal result, int scale) {
        if (result.scale() <= scale) {
            return result;
        }

        return result.setScale(scale, DEFAULT_ROUNDING_MODE);
    }

    public static String toOutputString(BigDecimal number) {
        return checkScale(number, OUTPUT_SCALE).toPlainString();
    }
}
