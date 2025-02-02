package foi.air.szokpt.accountmng.repositories;

import foi.air.szokpt.accountmng.entitites.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<UserRole,Integer> {
    Optional<UserRole> findByName(String name);
}
