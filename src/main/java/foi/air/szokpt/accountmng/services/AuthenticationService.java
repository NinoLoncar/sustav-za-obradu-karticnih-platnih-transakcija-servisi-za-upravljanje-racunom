package foi.air.szokpt.accountmng.services;

import foi.air.szokpt.accountmng.entitites.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthenticationService {
    private final List<User> users = List.of(new User("admin", "admin"));

    public boolean authenticate(String username, String password) {
        return users.stream().anyMatch(user ->
                user.getUsername().equals(username) && user.getPassword().equals(password));
    }
}
