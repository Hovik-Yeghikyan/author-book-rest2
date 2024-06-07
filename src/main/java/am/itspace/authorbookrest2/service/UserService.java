package am.itspace.authorbookrest2.service;

import am.itspace.authorbookrest2.dto.CreateUserRequestDto;
import am.itspace.authorbookrest2.dto.UserDto;
import am.itspace.authorbookrest2.entity.User;


public interface UserService {

    UserDto create(CreateUserRequestDto createUserRequestDto);
    User findByEmail(String email);


}