package ninja.afon.operator;

import ninja.afon.util.NumberUtil;

import java.math.BigDecimal;

public class Divide extends BaseOperator {

    @Override
    public int getParamsNum() {
        return 2;
    }

    @Override
    public BigDecimal proceed(BigDecimal a, BigDecimal b) {
        return a.divide(b, NumberUtil.DEFAULT_MATH_CONTEXT);
    }

    @Override
    public BigDecimal proceed() {
        return this.proceed(params.get(1), params.get(0));
    }

}
