package foi.air.szokpt.accountmng.services;

import foi.air.szokpt.accountmng.entitites.User;
import foi.air.szokpt.accountmng.entitites.UserRole;
import foi.air.szokpt.accountmng.exceptions.AuthorizationException;
import foi.air.szokpt.accountmng.exceptions.ValidationException;
import foi.air.szokpt.accountmng.repositories.RoleRepository;
import foi.air.szokpt.accountmng.repositories.UserRepository;
import foi.air.szokpt.accountmng.util.JwtUtil;
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
    private final JwtUtil jwtUtil;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, Validator<User> validator, Hasher hasher, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userDataValidator = validator;
        this.hasher = hasher;
        this.jwtUtil = jwtUtil;
    }

    public void registerUser(String authorizationHeader, User user) {
        authorizeAdmin(authorizationHeader);
        userDataValidator.validateData(user);
        assignRoleToUser(user);
        user.setEmail(user.getEmail().toLowerCase());
        user.setPassword(hasher.hashText(user.getPassword()));
        userRepository.save(user);
    }

    private void authorizeAdmin(String authorizationHeader) {
        String token = jwtUtil.extractToken(authorizationHeader);
        verifyToken(token);
        checkAdminRole(token);
    }

    private void verifyToken(String token) {
        jwtUtil.verifyToken(token);
    }

    private void checkAdminRole(String token) {
        String userRole = jwtUtil.getRoleName((token));
        if (!userRole.equals("admin")) {
            throw new AuthorizationException("This role is not authorized for this action");
        }
    }

    private void assignRoleToUser(User user) {
        Optional<UserRole> userRole = roleRepository.findByName(user.getRole().getName());
        UserRole role = userRole.orElseThrow(() ->
                new ValidationException("Role with name '" + user.getRole().getName() + "' does not exist"));
        user.setRole(role);
    }
}
