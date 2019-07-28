package au.com.nabgrocer.exception;

public class GroceryItemNotFoundException extends Exception {
    public GroceryItemNotFoundException(String message) {
        super(message);
    }
}
