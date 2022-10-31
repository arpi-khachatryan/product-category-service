package am.itspace.productcategoryservice.repository;

import am.itspace.productcategoryservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String username);

    boolean existsByEmail(String email);
}
