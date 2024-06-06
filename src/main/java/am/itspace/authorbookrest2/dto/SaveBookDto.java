package am.itspace.authorbookrest2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SaveBookDto {

    private String title;
    private String description;
    private double price;
    private int authorId;
}
