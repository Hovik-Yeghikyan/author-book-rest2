package am.itspace.authorbookrest2.service;

import am.itspace.authorbookrest2.dto.BookDto;
import am.itspace.authorbookrest2.dto.SaveBookDto;
import org.springframework.stereotype.Service;


public interface BookService {
    BookDto save(SaveBookDto saveBookDto);
}
