package com.example.booktracker.book_tracker_service.service;

import com.example.booktracker.book_tracker_service.dto.book.BookDTO;
import com.example.booktracker.book_tracker_service.entity.BookEntity;
import com.example.booktracker.book_tracker_service.mapper.author.AuthorMapper;
import com.example.booktracker.book_tracker_service.mapper.book.BookMapper;
import com.example.booktracker.book_tracker_service.repository.BookRepository;
import com.example.booktracker.book_tracker_service.service.impl.IBookService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService implements IBookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;

    @Override
    public List<BookDTO> getAll() {
        List<BookEntity> books = this.bookRepository.findAll();
        return books.stream()
                .map(this.bookMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<BookDTO> getAllPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<BookEntity> books = this.bookRepository.findAll(pageable);
        return books.map(this.bookMapper::toDTO);
    }

    @Override
    public Optional<BookDTO> getById(Long id) {
        return this.bookRepository.findById(id).map(this.bookMapper::toDTO);
    }

    @Transactional
    @Override
    public BookDTO create(BookDTO bookDTO) {
        BookEntity bookEntity = this.bookMapper.toEntity(bookDTO);
        BookEntity savedBook = this.bookRepository.save(bookEntity);
        return this.bookMapper.toDTO(savedBook);
    }

    @Override
    public Optional<BookDTO> update(Long id, BookDTO bookDTO) {

        return this.bookRepository.findById(id)
                .map(existingBook -> {
                    existingBook.setTitle(bookDTO.getTitle());
                    existingBook.setAuthor(this.authorMapper.toEntity(bookDTO.getAuthor()));
                    existingBook.setIsbn(bookDTO.getIsbn());
                    existingBook.setPublicationDate(bookDTO.getPublicationDate());
                    existingBook.setAvailable(bookDTO.isAvailable());
                    BookEntity bookMapper = this.bookRepository.save(existingBook);
                    return this.bookMapper.toDTO(bookMapper);
                });

    }

    @Override
    public Boolean delete(Long id) {
        return this.bookRepository.findById(id)
                .map(author -> {
                    this.bookRepository.delete(author);
                    return true;
                })
                .orElse(false);
    }

}
