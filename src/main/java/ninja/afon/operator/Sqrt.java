package ninja.afon.operator;

import ninja.afon.util.NumberUtil;

import java.math.BigDecimal;

public class Sqrt extends BaseOperator {

    @Override
    public int getParamsNum() {
        return 1;
    }

    @Override
    public BigDecimal proceed(BigDecimal a) {
        return NumberUtil.checkScale(a.sqrt(NumberUtil.DEFAULT_MATH_CONTEXT));
    }

    @Override
    public BigDecimal proceed() {
        return this.proceed(params.get(0));
    }

}
