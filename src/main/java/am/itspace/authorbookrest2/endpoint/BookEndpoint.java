package am.itspace.authorbookrest2.endpoint;

import am.itspace.authorbookrest2.dto.BookDto;
import am.itspace.authorbookrest2.dto.SaveBookDto;
import am.itspace.authorbookrest2.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/books")
public class BookEndpoint {

    private final BookService bookService;

    @PostMapping
    public BookDto create(@RequestBody SaveBookDto saveBookDto) {
       return bookService.save(saveBookDto);
    }
}
