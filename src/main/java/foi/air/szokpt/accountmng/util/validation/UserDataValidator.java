    package foi.air.szokpt.accountmng.util.validation;

    import foi.air.szokpt.accountmng.entitites.User;
    import foi.air.szokpt.accountmng.exceptions.ValidationException;
    import foi.air.szokpt.accountmng.repositories.UserRepository;
    import org.springframework.stereotype.Component;

    import java.util.regex.Pattern;

    @Component
    public class UserDataValidator implements Validator<User> {
        private final UserRepository userRepository;

        public UserDataValidator(UserRepository userRepository) {
            this.userRepository = userRepository;

        }

        public void validateData(User user) {
            validateRequiredFields(user);
            validateEmailFormat(user.getEmail());
            checkEmailAvailability(user.getEmail());
            checkUsernameAvailability(user.getUsername());
        }

        private void validateRequiredFields(User user) {
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

        private void validateEmailFormat(String email) {
            if (!checkEmailRegex(email)) {
                throw new ValidationException("Invalid email format");
            }
        }
        private boolean checkEmailRegex(String email) {
            final String emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
            return Pattern.matches(emailRegex, email);
        }

        private void checkEmailAvailability(String email) {
            email = email.toLowerCase();
            if (userRepository.existsByEmail(email)) {
                throw new ValidationException("User with this email already exists");
            }
        }

        private void checkUsernameAvailability(String username) {
            if (userRepository.existsByUsername(username)) {
                throw new ValidationException("User with this username already exists");
            }
        }
    }



