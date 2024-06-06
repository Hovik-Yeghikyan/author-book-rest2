package am.itspace.authorbookrest2.mapper;

import am.itspace.authorbookrest2.dto.AuthorResponseDto;
import am.itspace.authorbookrest2.dto.SaveAuthorDto;
import am.itspace.authorbookrest2.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring" /*,imports = LocalDate.class*/)
public interface AuthorMapper {
@Mapping(target = "firstName",source = "name")
    AuthorResponseDto map(Author author);
@Mapping(target = "createdDate", expression = "java(java.time.LocalDate.now())")
    Author map(SaveAuthorDto saveAuthorDto);
}
