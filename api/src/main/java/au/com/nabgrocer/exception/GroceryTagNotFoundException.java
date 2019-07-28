package au.com.nabgrocer.exception;

public class GroceryTagNotFoundException extends Exception {

    private static final long serialVersionUID = 5767053227739919276L;

    public GroceryTagNotFoundException(final String message) {
        super(message);
    }
}
