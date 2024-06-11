package am.itspace.authorbookrest2.service;

import am.itspace.authorbookrest2.dto.AuthorResponseDto;
import am.itspace.authorbookrest2.dto.PagingResponseDto;
import am.itspace.authorbookrest2.dto.SaveAuthorDto;
import org.springframework.data.domain.Pageable;


public interface AuthorService {
    AuthorResponseDto create(SaveAuthorDto author);

    PagingResponseDto getAll(Pageable pageable);

    AuthorResponseDto getById(int id);

    AuthorResponseDto update(int id, SaveAuthorDto author);

    void deleteById(int id);
}
