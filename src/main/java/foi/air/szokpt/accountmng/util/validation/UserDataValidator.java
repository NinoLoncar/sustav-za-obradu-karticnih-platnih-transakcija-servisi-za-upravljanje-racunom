package foi.air.szokpt.accountmng.util.validation;

import foi.air.szokpt.accountmng.entitites.User;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class UserDataValidator implements Validator<User> {

    public void validateData(User user) {
        validateRequiredFields(user);
        validateEmailFormat(user.getEmail());
    }

    private void validateRequiredFields(User user) {
        if (user.getEmail() == null || user.getEmail().isEmpty()) {
            throw new RuntimeException("Email cannot be null or empty");
        }
        if (user.getUsername() == null || user.getUsername().isEmpty()) {
            throw new RuntimeException("Username cannot be null or empty");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            throw new RuntimeException("Password cannot be null or empty");
        }
        if (user.getFirstName() == null || user.getFirstName().isEmpty()) {
            throw new RuntimeException("First name cannot be null or empty");
        }
        if (user.getLastName() == null || user.getLastName().isEmpty()) {
            throw new RuntimeException("Last name cannot be null or empty");
        }
        if (user.getRole() == null) {
            throw new RuntimeException("Role cannot be null");
        }
    }

    private void validateEmailFormat(String email) {
        if (!isValidEmail(email)) {
            throw new RuntimeException("Invalid email format");
        }
    }

    private boolean isValidEmail(String email) {
        final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }
}

