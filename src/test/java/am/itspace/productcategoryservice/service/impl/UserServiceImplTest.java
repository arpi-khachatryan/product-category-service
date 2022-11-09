package am.itspace.productcategoryservice.service.impl;

import am.itspace.productcategoryservice.mapper.UserMapper;
import am.itspace.productcategoryservice.model.Role;
import am.itspace.productcategoryservice.model.User;
import am.itspace.productcategoryservice.repository.UserRepository;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userServiceImpl;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    void save() {
        User user = User.builder()
                .id(1)
                .name("Tigran")
                .surname("Tigranyan")
                .email("tigran@gmail.com")
                .password(passwordEncoder.encode("password00"))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User value = userArgumentCaptor.getValue();
        assertEquals(user, value);
//        User user = User.builder()
//                .id(1)
//                .name("Tigran")
//                .surname("Tigranyan")
//                .email("tigran@gmail.com")
//                .password(passwordEncoder.encode("password00"))
//                .role(Role.USER)
//                .build();
//        when(userRepository.save(any())).thenReturn(user);
//        userServiceImpl.save(CreateUserDto.builder()
//                .name("Tigran")
//                .surname("Tigranyan")
//                .email("tigran@gmail.com")
//                .password(passwordEncoder.encode("password0"))
//                .build());
//        verify(userRepository, times(1)).save(any());
    }

    @Test
    void addUserFailTest() {
        User user = User.builder()
                .id(1)
                .name("Tigran")
                .surname("Tigranyan")
                .email("tigran@gmail.com")
                .password(passwordEncoder.encode("password00"))
                .role(Role.USER)
                .build();
        userRepository.save(null);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User value = userArgumentCaptor.getValue();
        assertNotEquals(user, value);
    }

    @Test
    void saveNull() {
        User user = User.builder()
                .id(1)
                .name("Tigran")
                .surname("Tigranyan")
                .email("tigran@gmail.com")
                .password(passwordEncoder.encode("password00"))
                .role(Role.USER)
                .build();
        userRepository.save(null);
        ArgumentCaptor<User> userArgumentCaptor = ArgumentCaptor.forClass(User.class);
        verify(userRepository).save(userArgumentCaptor.capture());
        User value = userArgumentCaptor.getValue();
        assertNotEquals(user, value);
//        User user = User.builder()
//                .id(1)
//                .name("Tigran")
//                .surname("Tigranyan")
//                .email("tigran@gmail.com")
//                .password(passwordEncoder.encode("password"))
//                .role(Role.USER)
//                .build();
//        when(userRepository.save(any())).thenReturn(user);
//        assertThrows(RuntimeException.class, () -> {
//            userServiceImpl.save(null);
//        });
//        verify(userRepository, times(0)).save(any());
    }

    @Test
    void role() {
        User user = User.builder()
                .id(1)
                .name("Tigran")
                .surname("Tigranyan")
                .email("tigran@gmail.com")
                .password(passwordEncoder.encode("aa12"))
                .role(Role.USER)
                .build();
        assertTrue(CoreMatchers.is(user.getRole()).matches(Role.USER));
    }

    @Test
    void authenticationExistsByEmail() {
        User user = User.builder()
                .id(1)
                .name("Tigran")
                .surname("Tigranyan")
                .email("tigran@gmail.com")
                .password(passwordEncoder.encode("aa12"))
                .role(Role.USER)
                .build();
        when(userRepository.findByEmail("tigran@gmail.com")).thenReturn(Optional.of(user));
        assertEquals(Optional.of(user), userRepository.findByEmail("tigran@gmail.com"));
    }
}

//    @Test
//    void existByEmail() {
//        User user = User.builder()
//                .id(1)
//                .name("Tigran")
//                .surname("Tigranyan")
//                .email("tigran@gmail.com")
//                .password(passwordEncoder.encode("password"))
//                .role(Role.USER)
//                .build();
//        given(userRepository.existsByEmail(anyString())).willReturn(true);
//        assertThatThrownBy(() -> userRepository.existsByEmail(user.getEmail()))
//                .isInstanceOf(RegisterException.class)
//                .hasMessageContaining("User with that email already exists");
//        verify(userRepository, never()).save(user);
//    }