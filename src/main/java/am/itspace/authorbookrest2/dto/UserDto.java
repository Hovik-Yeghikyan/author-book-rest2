package am.itspace.authorbookrest2.dto;

import am.itspace.authorbookrest2.entity.Gender;
import am.itspace.authorbookrest2.entity.UserType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private int id;
    private String name;
    private String surname;
    private String email;
    private UserType userType;
    private String imagePath;
}
