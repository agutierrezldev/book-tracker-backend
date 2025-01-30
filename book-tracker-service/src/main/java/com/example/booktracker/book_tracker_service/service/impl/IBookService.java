package com.example.booktracker.book_tracker_service.service.impl;

import com.example.booktracker.book_tracker_service.dto.author.AuthorDTO;
import com.example.booktracker.book_tracker_service.dto.book.BookDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface IBookService {

    List<BookDTO> getAll();

    Page<BookDTO> getAllPageable(int page, int size);

    Optional<BookDTO> getById(Long id);

    BookDTO create(BookDTO bookDTO);

    Optional<BookDTO> update(Long id, BookDTO bookDTO);

    Boolean delete(Long id);
}
