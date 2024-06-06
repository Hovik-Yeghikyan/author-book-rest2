package am.itspace.authorbookrest2.service;

import am.itspace.authorbookrest2.dto.AuthorResponseDto;
import am.itspace.authorbookrest2.dto.SaveAuthorDto;
import am.itspace.authorbookrest2.entity.Author;
import org.springframework.stereotype.Service;

import java.util.List;


public interface AuthorService {
    AuthorResponseDto create(SaveAuthorDto author);

    List<AuthorResponseDto> getAll();

    AuthorResponseDto getById(int id);

    AuthorResponseDto update(int id, SaveAuthorDto author);

    void deleteById(int id);
}
