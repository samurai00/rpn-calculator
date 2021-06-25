package ninja.afon.operator;

import ninja.afon.util.NumberUtil;

import java.math.BigDecimal;

public class Multiply extends BaseOperator {

    @Override
    public int getParamsNum() {
        return 2;
    }

    @Override
    public BigDecimal proceed(BigDecimal a, BigDecimal b) {
        return NumberUtil.checkScale(a.multiply(b));
    }

    @Override
    public BigDecimal proceed() {
        return this.proceed(params.get(1), params.get(0));
    }

}
