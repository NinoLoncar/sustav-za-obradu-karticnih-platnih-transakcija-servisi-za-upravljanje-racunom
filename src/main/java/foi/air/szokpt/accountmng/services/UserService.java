package foi.air.szokpt.accountmng.services;

import foi.air.szokpt.accountmng.entitites.User;
import foi.air.szokpt.accountmng.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user) {
        this.userRepository.save(user);
    }

}
