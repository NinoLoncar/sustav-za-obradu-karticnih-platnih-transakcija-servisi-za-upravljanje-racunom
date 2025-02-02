package foi.air.szokpt.accountmng.exceptions;

public class ValidationException extends RuntimeException {
    public ValidationException(String message) {
        super("Validation failed: " + message);
    }
}
