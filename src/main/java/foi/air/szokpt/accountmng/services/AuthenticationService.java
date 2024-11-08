package foi.air.szokpt.accountmng.services;

import foi.air.szokpt.accountmng.entitites.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthenticationService {
    private final List<User> users = List.of(new User("admin", "admin"));

    public Optional<User> authenticate(String username, String password) {
        return users.stream().filter(user ->
                        user.getUsername().equals(username) && user.getPassword().equals(password))
                .findFirst();
    }
}
