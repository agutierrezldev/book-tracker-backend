package com.example.booktracker.book_tracker_service.service.impl;

import com.example.booktracker.book_tracker_service.dto.author.AuthorDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IAuthorService {

    List<AuthorDTO> getAll();

    Page<AuthorDTO> getAllPageable(int page, int size);

    Optional<AuthorDTO> getById(Long id);

    AuthorDTO create(AuthorDTO authorDTO);

    Optional<AuthorDTO> update(Long id, AuthorDTO authorDTO);

    Boolean delete(Long id);
}
