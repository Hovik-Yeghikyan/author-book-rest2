package am.itspace.authorbookrest2.service;

import am.itspace.authorbookrest2.dto.CreateUserRequestDto;
import am.itspace.authorbookrest2.dto.UserDto;
import am.itspace.authorbookrest2.entity.User;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


public interface UserService {

    UserDto create(CreateUserRequestDto createUserRequestDto);
    User findByEmail(String email);
    User findById(int id);


    void uploadImage(User byId, MultipartFile multipartFile) throws IOException;
}