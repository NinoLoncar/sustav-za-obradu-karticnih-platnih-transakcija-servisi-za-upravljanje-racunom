package foi.air.szokpt.accountmng.repositories;

import foi.air.szokpt.accountmng.entitites.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);

    boolean existsByEmail(@Param("email") String email);

    boolean existsByUsername(@Param("username") String username);

    boolean existsByEmailAndIdNot(@Param("email") String email, @Param("id") int id);

    boolean existsByUsernameAndIdNot(@Param("username") String username, @Param("id") int id);
}
