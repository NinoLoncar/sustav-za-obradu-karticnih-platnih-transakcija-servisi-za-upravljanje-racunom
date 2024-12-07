package foi.air.szokpt.accountmng.util.validation;

import foi.air.szokpt.accountmng.entitites.User;
import foi.air.szokpt.accountmng.exceptions.ValidationException;
import foi.air.szokpt.accountmng.repositories.UserRepository;

import java.util.regex.Pattern;

public abstract class UserDataValidator implements Validator<User> {
    protected final UserRepository userRepository;

    public UserDataValidator(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    public abstract void validateData(User user);

    protected void validateRequiredFields(User user) {
        validateField(user.getEmail(), "Email cannot be null or empty");
        validateField(user.getUsername(), "Username cannot be null or empty");
        validateField(user.getPassword(), "Password cannot be null or empty");
        validateField(user.getFirstName(), "First name cannot be null or empty");
        validateField(user.getLastName(), "Last name cannot be null or empty");
        if (user.getRole() == null)
            throw new ValidationException("User role cannot be null or empty");
        validateField(user.getRole().getName(), "User role cannot be null or empty");
    }

    private void validateField(String field, String errorMessage) {
        if (field == null || field.isEmpty()) {
            throw new ValidationException(errorMessage);
        }
    }

    protected void validateEmailFormat(String email) {
        if (!checkEmailRegex(email)) {
            throw new ValidationException("Invalid email format");
        }
    }

    private boolean checkEmailRegex(String email) {
        final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return Pattern.matches(emailRegex, email);
    }

}



