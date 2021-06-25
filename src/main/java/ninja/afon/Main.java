package ninja.afon;

public class Main {

    public static void main(String[] args) {
	    Calculator calculator = new Calculator();
        calculator.getOut().println("RPN Calculator");
        calculator.waitInput();
    }
}
