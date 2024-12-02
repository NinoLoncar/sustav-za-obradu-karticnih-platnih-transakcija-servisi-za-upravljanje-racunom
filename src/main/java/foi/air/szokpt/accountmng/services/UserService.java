package foi.air.szokpt.accountmng.services;

import foi.air.szokpt.accountmng.entitites.User;
import foi.air.szokpt.accountmng.repositories.UserRepository;
import foi.air.szokpt.accountmng.util.hashing.Hasher;
import foi.air.szokpt.accountmng.util.validation.Validator;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final Validator<User> userDataValidator;
    private final Hasher hasher;

    public UserService(UserRepository userRepository, Validator<User> validator, Hasher hasher) {
        this.userRepository = userRepository;
        this.userDataValidator = validator;
        this.hasher = hasher;
    }

    public void registerUser(User user) {
        userDataValidator.validateData(user);
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("User with this email already exists");
        }
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("User with this username already exists");
        }

        user.setPassword(hasher.hashText(user.getPassword()));
        userRepository.save(user);
    }

}
