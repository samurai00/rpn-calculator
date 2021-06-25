package ninja.afon.io;

import java.util.List;

public interface IStdOut {
    void println(String text);

    void print(String text);

    void printStack(List<String> stack);

    void printStack(List<String> stack, String prefix);
}
