package foi.air.szokpt.accountmng.util.validation;

import foi.air.szokpt.accountmng.entitites.User;
import foi.air.szokpt.accountmng.exceptions.ValidationException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserDataValidator implements Validator<User> {

    public void validateData(User user) {
        validateRequiredFields(user);
        validateEmailFormat(user.getEmail());
    }

    private void validateRequiredFields(User user) {
        validateField(user.getEmail(), "Email cannot be null or empty");
        validateField(user.getUsername(), "Username cannot be null or empty");
        validateField(user.getPassword(), "Password cannot be null or empty");
        validateField(user.getFirstName(), "First name cannot be null or empty");
        validateField(user.getLastName(), "Last name cannot be null or empty");
        if (user.getRole() == null) {
            throw new ValidationException("Role cannot be null");
        }
    }

    private void validateField(String field, String errorMessage) {
        if (field == null || field.isEmpty()) {
            throw new ValidationException(errorMessage);
        }
    }

    private void validateEmailFormat(String email) {
        if (!isValidEmail(email)) {
            throw new ValidationException("Invalid email format");
        }
    }

    private boolean isValidEmail(String email) {
        final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }
}

