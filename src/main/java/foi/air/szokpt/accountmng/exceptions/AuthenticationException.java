package foi.air.szokpt.accountmng.exceptions;

public class AuthenticationException extends RuntimeException{
    public AuthenticationException(String message) {
        super("Authentication failed: "+message);
    }
}
