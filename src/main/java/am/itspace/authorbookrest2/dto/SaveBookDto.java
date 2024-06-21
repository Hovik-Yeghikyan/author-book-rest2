package am.itspace.authorbookrest2.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.validator.constraints.Length;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveBookDto {
    @NotNull(message = "Title is required!")
    @Length(min = 2, message = "Title length should be >= 2")
    private String title;
    @NotEmpty(message = "Description is required")
    private String description;
    private double price;
    private int authorId;
}
