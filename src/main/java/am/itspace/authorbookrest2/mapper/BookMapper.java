package am.itspace.authorbookrest2.mapper;

import am.itspace.authorbookrest2.dto.BookDto;
import am.itspace.authorbookrest2.dto.SaveBookDto;
import am.itspace.authorbookrest2.entity.Book;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = AuthorMapper.class)
public interface BookMapper {
    @Mapping(target = "authorResponseDto", source = "author")
    BookDto map(Book book);

    Book map(SaveBookDto saveBookDto);

    List<BookDto> map(List<Book> books);
}
