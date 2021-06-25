package ninja.afon;

import ninja.afon.exception.InsufficientParametersException;

import java.math.BigDecimal;
import java.util.LinkedList;

public interface IOperator {
    int getParamsNum();

    default BigDecimal proceed(BigDecimal a, BigDecimal b) {
        throw new UnsupportedOperationException();
    }

    default BigDecimal proceed(BigDecimal a) {
        throw new UnsupportedOperationException();
    }

    BigDecimal proceed();

    void handleParams(LinkedList<String> commandStack) throws InsufficientParametersException;
}
