package am.itspace.authorbookrest2.mapper;

import am.itspace.authorbookrest2.dto.CreateUserRequestDto;
import am.itspace.authorbookrest2.dto.UserDto;
import am.itspace.authorbookrest2.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" )
public interface UserMapper {

    UserDto map(User user);
@Mapping(target = "userType",constant = "USER")
    User map(CreateUserRequestDto createUserRequestDto);
}
