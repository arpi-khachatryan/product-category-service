package am.itspace.productcategoryservice.repository;

import am.itspace.productcategoryservice.model.Role;
import am.itspace.productcategoryservice.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
    }

    @Test
    void findByEmail() {
        String email = "yan@gmail.com";
        User user = User.builder()
                .email("yan@gmail.com")
                .name("Victoria")
                .password(passwordEncoder.encode("password"))
                .role(Role.USER)
                .surname("Yan")
                .build();
        userRepository.save(user);
        Optional<User> byEmail = userRepository.findByEmail(email);
        assertEquals(user, byEmail.get());
    }

    @Test
    void existsByEmail() {
        String email = "yan@gmail.com";
        User user = User.builder()
                .email(email)
                .name("Victoria")
                .password(passwordEncoder.encode("password"))
                .role(Role.USER)
                .surname("Yan")
                .build();
        userRepository.save(user);
        boolean expected = userRepository.existsByEmail(email);
        assertTrue(expected);
    }
}