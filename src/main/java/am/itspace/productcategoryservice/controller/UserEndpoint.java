package am.itspace.productcategoryservice.controller;


import am.itspace.productcategoryservice.dto.CreateUserDto;
import am.itspace.productcategoryservice.dto.UserAuthDto;
import am.itspace.productcategoryservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserEndpoint {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> register(@RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body((userService.save(createUserDto)));
    }

    @PostMapping("/auth")
    public ResponseEntity<?> auth(@RequestBody UserAuthDto userAuthDto) {
        return ResponseEntity.ok(userService.authentication(userAuthDto));
    }
}
