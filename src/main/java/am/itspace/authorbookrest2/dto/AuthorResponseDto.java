package am.itspace.authorbookrest2.dto;

import am.itspace.authorbookrest2.entity.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthorResponseDto {

    private int id;
    private String firstName;
    private String surname;
    private Gender gender;
    private int age;
}
