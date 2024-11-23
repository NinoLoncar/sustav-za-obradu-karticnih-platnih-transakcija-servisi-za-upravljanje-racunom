package foi.air.szokpt.accountmng.services;

import foi.air.szokpt.accountmng.entitites.User;
import foi.air.szokpt.accountmng.repositories.UserRepository;
import foi.air.szokpt.accountmng.util.validation.UserDataValidator;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    UserDataValidator userDataValidator;

    public UserService(UserRepository userRepository, UserDataValidator userDataValidator) {
        this.userRepository = userRepository;
        this.userDataValidator = userDataValidator;
    }


    public void registerUser(User user) {
        userDataValidator.validateRequiredFields(user);
        userDataValidator.validateEmailFormat(user.getEmail());
        userDataValidator.validateEmailUniqueness(user.getEmail());
        userDataValidator.validateUsernameUniqueness(user.getUsername());

        userRepository.save(user);
    }

}
