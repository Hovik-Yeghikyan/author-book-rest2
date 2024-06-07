package am.itspace.authorbookrest2.endpoint;

import am.itspace.authorbookrest2.dto.AuthRequestDto;
import am.itspace.authorbookrest2.dto.AuthResponseDto;
import am.itspace.authorbookrest2.dto.CreateUserRequestDto;
import am.itspace.authorbookrest2.dto.UserDto;
import am.itspace.authorbookrest2.entity.User;
import am.itspace.authorbookrest2.mapper.UserMapper;
import am.itspace.authorbookrest2.service.UserService;
import am.itspace.authorbookrest2.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/users")
public class UserEndpoint {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping
    public ResponseEntity<UserDto> register(@RequestBody CreateUserRequestDto createUserRequestDto) {
        User byEmail = userService.findByEmail(createUserRequestDto.getEmail());
        if (byEmail != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(userService.create(createUserRequestDto));
    }


    @PostMapping("/auth")
    public ResponseEntity<AuthResponseDto> login(@RequestBody AuthRequestDto authRequestDto) {
        User user = userService.findByEmail(authRequestDto.getEmail());
        if (user == null || !passwordEncoder.matches(authRequestDto.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(AuthResponseDto.builder()
                .token(jwtTokenUtil.generateToken(user.getEmail()))
                .userDto(userMapper.map(user))
                .build());
    }

}
