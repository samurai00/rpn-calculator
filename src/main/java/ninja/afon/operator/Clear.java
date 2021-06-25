package ninja.afon.operator;

import java.math.BigDecimal;
import java.util.LinkedList;

public class Clear extends BaseOperator {

    @Override
    public int getParamsNum() {
        return 0;
    }

    @Override
    public BigDecimal proceed() {
        return null;
    }

    @Override
    public void handleParams(LinkedList<String> commandStack) {
        commandStack.clear();
    }

}
