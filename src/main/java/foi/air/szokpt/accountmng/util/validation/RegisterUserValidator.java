package foi.air.szokpt.accountmng.util.validation;

import foi.air.szokpt.accountmng.entitites.User;
import foi.air.szokpt.accountmng.exceptions.ValidationException;
import foi.air.szokpt.accountmng.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("registerUserValidator")
public class RegisterUserValidator extends UserDataValidator {

    public RegisterUserValidator(UserRepository userRepository) {
        super(userRepository);
    }

    public void validateData(User user) {
        validateRequiredFields(user);
        validateEmailFormat(user.getEmail());
        checkEmailAvailability(user.getEmail());
        checkUsernameAvailability(user.getUsername());
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
