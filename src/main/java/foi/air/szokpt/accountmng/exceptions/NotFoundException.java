package foi.air.szokpt.accountmng.exceptions;

public class NotFoundException extends RuntimeException {
    public NotFoundException() {
        super("Resource not found");
    }
}
