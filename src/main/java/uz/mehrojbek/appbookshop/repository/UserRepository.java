package uz.mehrojbek.appbookshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.mehrojbek.appbookshop.entity.User;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByUsername(String username);
    boolean existsByUsername(String username);
    boolean existsByUsernameAndIdNot(String username, UUID id);
}
