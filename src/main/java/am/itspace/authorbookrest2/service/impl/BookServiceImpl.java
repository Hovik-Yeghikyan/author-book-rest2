package am.itspace.authorbookrest2.service.impl;

import am.itspace.authorbookrest2.dto.BookDto;
import am.itspace.authorbookrest2.dto.SaveBookDto;
import am.itspace.authorbookrest2.entity.Book;
import am.itspace.authorbookrest2.mapper.BookMapper;
import am.itspace.authorbookrest2.repository.AuthorRepository;
import am.itspace.authorbookrest2.repository.BookRepository;
import am.itspace.authorbookrest2.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final BookMapper bookMapper;

    @Override
    public BookDto save(SaveBookDto saveBookDto) {
        Book book = bookMapper.map(saveBookDto);
        book.setAuthor(authorRepository.findById(saveBookDto.getAuthorId()).orElse(null));
        bookRepository.save(book);
        return bookMapper.map(book);

    }
}
