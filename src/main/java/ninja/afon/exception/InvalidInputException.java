package ninja.afon.exception;

public class InvalidInputException extends Exception {

    private int position;

    public InvalidInputException() {
        super();
    }

    public InvalidInputException(int pos) {
        super();
        this.position = pos;
    }

    public InvalidInputException(String message) {
        super(message);
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
