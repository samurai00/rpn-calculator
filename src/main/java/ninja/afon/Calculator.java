package ninja.afon;

import ninja.afon.exception.InsufficientParametersException;
import ninja.afon.exception.InvalidInputException;
import ninja.afon.io.IStdIn;
import ninja.afon.io.IStdOut;
import ninja.afon.io.impl.SystemIn;
import ninja.afon.io.impl.SystemOut;
import ninja.afon.operator.Clear;
import ninja.afon.operator.Undo;
import ninja.afon.util.NumberUtil;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author afon
 */
public class Calculator {
    private final LinkedList<String> history;

    private final LinkedList<String> commandStack;

    private final LinkedList<String> allCommandStack;

    private final IStdOut out;

    private final IStdIn in;

    private final Scanner scanner;

    private static final String DELIMITER = " ";

    public Calculator() {
        this.out = new SystemOut();
        this.in = new SystemIn();

        this.scanner = new Scanner(this.in.getInputStream());

        this.history = new LinkedList<>();
        this.commandStack = new LinkedList<>();
        this.allCommandStack = new LinkedList<>();
    }

    public void waitInput() {
        while (true) {
            String str = this.scanner.nextLine();
            if ("exit".equals(str)) {
                break;
            }
            List<String> currentInputList = this.parseInputData(str);
            try {
                this.processInput(currentInputList, false);
            } catch (InvalidInputException e) {
                String s = this.commandStack.pop();
                this.out.println("invalid input '" + s + "' (position: " + e.getPosition() + ")");
            } catch (InsufficientParametersException e) {
                String op = this.commandStack.pop();
                this.out.println("operator " + op + " (position: " + e.getPosition() + "): insufficient parameters");
            }
            this.out.printStack(this.commandStack);
        }

        this.scanner.close();
    }

    public List<String> parseInputData(String inputStr) {
        if (StringUtils.isBlank(inputStr)) {
            return Collections.emptyList();
        }

        return new ArrayList<>(Arrays.asList(inputStr.split(DELIMITER)));
    }

    private void processInput(List<String> currentInputList, boolean rerun) throws InvalidInputException, InsufficientParametersException {
        int pos = 1;
        for (String s : currentInputList) {
            processOneInput(this.commandStack, s, pos, rerun);
            pos = pos + s.length() + 1;
        }
    }

    private void processOneInput(LinkedList<String> commands, String s, int pos, boolean rerun) throws InvalidInputException, InsufficientParametersException {
        if (!rerun) {
            this.history.push(s);
        }
        commands.push(s);

        BigDecimal number;
        boolean isValidNumber = false;
        try {
            number = NumberUtil.parseNumber(s);
            commands.pop();
            commands.push(number.toPlainString());
            if (!rerun) {
                this.allCommandStack.push(commands.peek());
            }
            isValidNumber = true;
        } catch (NumberFormatException e) {
            // ignore
        }

        if (OperatorFactory.isOperator(s)) {
            try {
                processOneCommand(commands, rerun);
            } catch (InsufficientParametersException e) {
                e.setPosition(pos);
                throw e;
            }
        } else if (!isValidNumber) {
            throw new InvalidInputException(pos);
        }
    }

    private void processOneCommand(LinkedList<String> commands, boolean rerun) throws InsufficientParametersException, InvalidInputException {
        String cmd = commands.pop();
        IOperator operator = OperatorFactory.create(cmd);
        try {
            operator.handleParams(commands);
            BigDecimal result = operator.proceed();
            if (result != null) {
                commands.push(result.toPlainString());
                if (!rerun) {
                    this.allCommandStack.push(cmd);
                }
            } else if (operator instanceof Clear) {
                operator.handleParams(this.allCommandStack);
            } else if (operator instanceof Undo) {
                String undoCmd = this.allCommandStack.pop();
                if (OperatorFactory.isOperator(undoCmd)) {
                    undoCommands(commands);
                }
            }
        } catch (InsufficientParametersException e) {
            // push back
            commands.push(cmd);
            throw e;
        }
    }

    public void undoCommands(LinkedList<String> commands) throws InsufficientParametersException, InvalidInputException {
        commands.clear();
        List<String> stash = new LinkedList<>(this.allCommandStack);

        ListIterator<String> iterator = stash.listIterator(stash.size());
        while (iterator.hasPrevious()) {
            processOneInput(commands, iterator.previous(), 0, true);
        }
    }

    public IStdOut getOut() {
        return this.out;
    }
}
