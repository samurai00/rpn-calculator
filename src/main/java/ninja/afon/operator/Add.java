package ninja.afon.operator;

import java.math.BigDecimal;

public class Add extends BaseOperator {

    @Override
    public int getParamsNum() {
        return 2;
    }

    @Override
    public BigDecimal proceed(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    @Override
    public BigDecimal proceed() {
        return this.proceed(params.get(1), params.get(0));
    }

}
