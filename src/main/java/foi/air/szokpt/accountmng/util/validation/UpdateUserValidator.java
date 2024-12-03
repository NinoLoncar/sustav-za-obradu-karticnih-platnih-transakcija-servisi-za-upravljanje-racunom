package foi.air.szokpt.accountmng.util.validation;

import foi.air.szokpt.accountmng.entitites.User;
import foi.air.szokpt.accountmng.exceptions.ValidationException;
import foi.air.szokpt.accountmng.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("updateUserValidator")
public class UpdateUserValidator extends UserDataValidator {
    public UpdateUserValidator(UserRepository userRepository) {
        super(userRepository);
    }

    public void validateData(User user) {
        validateRequiredFields(user);
        validateEmailFormat(user.getEmail());
        checkEmailAvailability(user.getEmail(), user.getId());
        checkUsernameAvailability(user.getUsername(), user.getId());
    }

    private void checkEmailAvailability(String email, Integer id) {
        email = email.toLowerCase();
        if (userRepository.existsByEmailAndIdNot(email, id)) {
            throw new ValidationException("User with this email already exists");
        }
    }

    private void checkUsernameAvailability(String username, Integer id) {
        if (userRepository.existsByUsernameAndIdNot(username, id)) {
            throw new ValidationException("User with this username already exists");
        }
    }
}
