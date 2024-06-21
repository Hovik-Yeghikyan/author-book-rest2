package am.itspace.authorbookrest2.endpoint;

import am.itspace.authorbookrest2.dto.BookDto;
import am.itspace.authorbookrest2.dto.BookFilterDto;
import am.itspace.authorbookrest2.dto.SaveBookDto;
import am.itspace.authorbookrest2.service.BookService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/books")
public class BookEndpoint {

    private final BookService bookService;

    @PostMapping
    public BookDto create(@Valid @RequestBody SaveBookDto saveBookDto) {
       return bookService.save(saveBookDto);
    }

    @GetMapping
    public List<BookDto> getAll() {
        return bookService.getAll();
    }

    @PostMapping("/filter")
    public List<BookDto> getAllByFilter(@RequestBody BookFilterDto bookFilterDto) {
        return bookService.getAllByFilter(bookFilterDto);
    }
}
