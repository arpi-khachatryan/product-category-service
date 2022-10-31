package am.itspace.productcategoryservice.service.impl;

import am.itspace.productcategoryservice.dto.CreateUserDto;
import am.itspace.productcategoryservice.dto.UserAuthDto;
import am.itspace.productcategoryservice.dto.UserAuthResponceDto;
import am.itspace.productcategoryservice.dto.UserResponceDto;
import am.itspace.productcategoryservice.exception.AuthenticationException;
import am.itspace.productcategoryservice.exception.Error;
import am.itspace.productcategoryservice.exception.RegisterException;
import am.itspace.productcategoryservice.mapper.UserMapper;
import am.itspace.productcategoryservice.model.Role;
import am.itspace.productcategoryservice.model.User;
import am.itspace.productcategoryservice.repository.UserRepository;
import am.itspace.productcategoryservice.service.UserService;
import am.itspace.productcategoryservice.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public UserResponceDto save(CreateUserDto createUserDto) {
        if (userRepository.existsByEmail(createUserDto.getEmail())) {
            throw new RegisterException(Error.USER_REGISTRATION_FAILED);
        }
        User user = userMapper.mapToEntity(createUserDto);
        user.setRole(Role.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.map(userRepository.save(user));
    }

    @Override
    public UserAuthResponceDto authentication(UserAuthDto userAuthDto) {
        User user = userRepository.findByEmail(userAuthDto.getEmail()).orElseThrow(() -> new AuthenticationException(Error.USER_NOT_FOUND));
        log.info("User with that email not found");
        if (passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword())) {
            return UserAuthResponceDto.builder()
                    .token(jwtTokenUtil.generateToken(user.getEmail()))
                    .user(userMapper.map(user))
                    .build();
        }
        return null;
    }
}



