package am.itspace.authorbookrest2.service.impl;

import am.itspace.authorbookrest2.dto.CreateUserRequestDto;
import am.itspace.authorbookrest2.dto.UserDto;
import am.itspace.authorbookrest2.entity.User;
import am.itspace.authorbookrest2.mapper.UserMapper;
import am.itspace.authorbookrest2.repository.UserRepository;
import am.itspace.authorbookrest2.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    @Value("${upload.image.path}")
    private String uploadImagePath;


    @Override
    public UserDto create(CreateUserRequestDto createUserRequestDto) {
        User user = userMapper.map(createUserRequestDto);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.map(userRepository.save(user));
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }

    @Override
    public User findById(int id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public void uploadImage(User user, MultipartFile multipartFile) throws IOException {
        if (multipartFile != null && !multipartFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + " " + multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(uploadImagePath,  fileName));
            user.setImagePath(fileName);
            userRepository.save(user);
        }
    }
}
