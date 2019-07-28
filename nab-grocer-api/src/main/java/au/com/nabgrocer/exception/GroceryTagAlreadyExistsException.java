package au.com.nabgrocer.exception;

public class GroceryTagAlreadyExistsException extends Exception {

    private static final long serialVersionUID = 4396308635747190243L;

    public GroceryTagAlreadyExistsException(final String message) {
        super(message);
    }
}
