package foi.air.szokpt.accountmng.services;

import foi.air.szokpt.accountmng.entitites.User;
import foi.air.szokpt.accountmng.repositories.UserRepository;
import foi.air.szokpt.accountmng.util.validation.Validator;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    Validator<User> userDataValidator;

    public UserService(UserRepository userRepository, Validator<User> validator) {
        this.userRepository = userRepository;
        this.userDataValidator = validator;
    }

    public void registerUser(User user) {
        userDataValidator.validateData(user);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User with this username already exists");
        }
        userRepository.save(user);
    }

}
