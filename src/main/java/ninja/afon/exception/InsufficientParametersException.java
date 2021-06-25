package ninja.afon.exception;

public class InsufficientParametersException extends Exception {

    private int position;

    public InsufficientParametersException() {
        super();
    }

    public InsufficientParametersException(String message) {
        super(message);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
