package ninja.afon.operator;

import ninja.afon.IOperator;
import ninja.afon.exception.InsufficientParametersException;
import ninja.afon.util.NumberUtil;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public abstract class BaseOperator implements IOperator {

    protected List<BigDecimal> params;

    protected BaseOperator() {
        params = new ArrayList<>(this.getParamsNum());
    }

    @Override
    public void handleParams(LinkedList<String> commandStack) throws InsufficientParametersException {
        int i = this.getParamsNum();
        if (commandStack.size() < i) {
            throw new InsufficientParametersException();
        }
        while (i-- > 0) {
            params.add(NumberUtil.parseNumber(Objects.requireNonNull(commandStack.pop())));
        }
    }

}
