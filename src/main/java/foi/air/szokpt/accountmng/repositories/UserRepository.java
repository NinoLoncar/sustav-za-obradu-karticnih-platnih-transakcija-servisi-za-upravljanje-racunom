package foi.air.szokpt.accountmng.repositories;

import foi.air.szokpt.accountmng.entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface  UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUsername(String username);
}
