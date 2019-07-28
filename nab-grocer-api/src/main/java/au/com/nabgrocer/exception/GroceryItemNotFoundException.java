package au.com.nabgrocer.exception;

public class GroceryItemNotFoundException extends Exception {

    private static final long serialVersionUID = -600601032695760372L;

    public GroceryItemNotFoundException(final String message) {
        super(message);
    }
}
