package am.itspace.authorbookrest2.endpoint;

import am.itspace.authorbookrest2.dto.AuthorResponseDto;
import am.itspace.authorbookrest2.dto.SaveAuthorDto;
import am.itspace.authorbookrest2.service.AuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("v1/authors")
public class AuthorEndpoint {

    private final AuthorService authorService;

    @PostMapping
    public AuthorResponseDto createAuthor(@RequestBody SaveAuthorDto authorDto) {
       return authorService.create(authorDto);
    }

    @GetMapping
    public List<AuthorResponseDto> getAll() {
        return authorService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getById(@PathVariable("id") int id) {
      AuthorResponseDto author = authorService.getById(id);
      if (author == null) {
         return ResponseEntity.notFound().build();
      }
      return ResponseEntity.ok(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> update(@PathVariable("id") int id, @RequestBody SaveAuthorDto authorDto) {
        AuthorResponseDto byId = authorService.getById(id);
        if (byId == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(authorService.update(id,authorDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> deleteById(@PathVariable("id") int id) {
        AuthorResponseDto byId = authorService.getById(id);
        if (byId == null) {
            return ResponseEntity.notFound().build();
        }
        authorService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
