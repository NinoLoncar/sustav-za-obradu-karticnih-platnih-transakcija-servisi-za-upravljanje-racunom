package foi.air.szokpt.accountmng.services;

import foi.air.szokpt.accountmng.entitites.User;
import foi.air.szokpt.accountmng.entitites.UserRole;
import foi.air.szokpt.accountmng.exceptions.ValidationException;
import foi.air.szokpt.accountmng.repositories.RoleRepository;
import foi.air.szokpt.accountmng.repositories.UserRepository;
import foi.air.szokpt.accountmng.util.hashing.Hasher;
import foi.air.szokpt.accountmng.util.validation.Validator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final Validator<User> userDataValidator;
    private final Hasher hasher;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, Validator<User> validator, Hasher hasher) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDataValidator = validator;
        this.hasher = hasher;
    }

    public void registerUser(User user) {
        user.setEmail(user.getEmail().toLowerCase());
        userDataValidator.validateData(user);
        assignRoleToUser(user);
        user.setPassword(hasher.hashText(user.getPassword()));
        userRepository.save(user);
    }

    private void assignRoleToUser(User user) {
        Optional<UserRole> userRole = roleRepository.findByName(user.getRole().getName());
        UserRole role = userRole.orElseThrow(() ->
                new ValidationException("Role with name '" + user.getRole().getName() + "' does not exist"));
        user.setRole(role);
    }

}
