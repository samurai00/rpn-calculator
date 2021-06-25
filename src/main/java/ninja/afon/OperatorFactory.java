package ninja.afon;

import ninja.afon.operator.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author afon
 */
public class OperatorFactory {

    private OperatorFactory() {
    }

    private static final Map<String, Class> OPERATOR_MAP;

    static {
        OPERATOR_MAP = new HashMap<>();
        OPERATOR_MAP.put("+", Add.class);
        OPERATOR_MAP.put("-", Sub.class);
        OPERATOR_MAP.put("*", Multiply.class);
        OPERATOR_MAP.put("/", Divide.class);
        OPERATOR_MAP.put("sqrt", Sqrt.class);
        OPERATOR_MAP.put("clear", Clear.class);
        OPERATOR_MAP.put("undo", Undo.class);
    }

    public static boolean isOperator(String command) {
        return OPERATOR_MAP.containsKey(command);
    }


    public static IOperator create(String command) {
        if (!OPERATOR_MAP.containsKey(command)) {
            throw new IllegalArgumentException();
        }

        Class klass = OPERATOR_MAP.get(command);
        try {
            return (IOperator) klass.getDeclaredConstructor().newInstance();
        } catch (InstantiationException | InvocationTargetException
                | IllegalAccessException | NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
}
