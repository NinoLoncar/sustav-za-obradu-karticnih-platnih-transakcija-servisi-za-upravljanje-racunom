package foi.air.szokpt.accountmng.exceptions;

public class AuthorizationException extends RuntimeException{
    public AuthorizationException(String message) {
        super("Authorization failed: " + message);
    }
}
