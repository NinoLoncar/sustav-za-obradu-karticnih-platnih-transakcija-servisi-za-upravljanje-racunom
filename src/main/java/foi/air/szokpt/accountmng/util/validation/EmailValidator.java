package foi.air.szokpt.accountmng.util.validation;

import java.util.regex.Pattern;

public class EmailValidator {
    private static final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    public static boolean isValidEmail(String email) {
        return Pattern.matches(emailRegex, email);
    }
}
