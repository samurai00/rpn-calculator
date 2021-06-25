package ninja.afon.io.impl;

import ninja.afon.io.IStdOut;
import ninja.afon.util.NumberUtil;

import java.math.BigDecimal;
import java.util.List;
import java.util.ListIterator;

public class SystemOut implements IStdOut {

    private static final String DEFAULT_PREFIX = "stack:";

    @Override
    public void println(String text) {
        System.out.println(text);
    }

    @Override
    public void print(String text) {
        System.out.print(text);
    }

    @Override
    public void printStack(List<String> stack) {
        printStack(stack, DEFAULT_PREFIX);
    }

    @Override
    public void printStack(List<String> stack, String prefix) {
        StringBuilder sb = new StringBuilder();
        sb.append(prefix);
        ListIterator<String> iterator = stack.listIterator(stack.size());
        while (iterator.hasPrevious()) {
            sb.append(" ");
            String s = iterator.previous();
            try {
                BigDecimal number = NumberUtil.parseNumber(s);
                sb.append(NumberUtil.toOutputString(number));
            } catch (NumberFormatException e) {
                sb.append(s);
            }
        }
        println(sb.toString());
    }
}
