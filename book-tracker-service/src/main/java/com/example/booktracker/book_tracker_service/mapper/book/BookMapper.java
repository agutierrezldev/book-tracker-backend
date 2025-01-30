package com.example.booktracker.book_tracker_service.mapper.book;

import com.example.booktracker.book_tracker_service.dto.book.BookDTO;
import com.example.booktracker.book_tracker_service.entity.BookEntity;
import com.example.booktracker.book_tracker_service.mapper.author.AuthorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BookMapper {

    private final AuthorMapper authorMapper;

    public BookDTO toDTO(BookEntity bookEntity) {
        if (bookEntity == null) {
            return null;
        }

        return BookDTO.builder()
                .id(bookEntity.getId())
                .title(bookEntity.getTitle())
                .author(this.authorMapper.toDTO(bookEntity.getAuthor()))
                .isbn(bookEntity.getIsbn())
                .publicationDate(bookEntity.getPublicationDate())
                .available(bookEntity.isAvailable())
                .build();
    }

    public BookEntity toEntity(BookDTO bookDTO) {
        if (bookDTO == null) {
            return null;
        }

        return BookEntity.builder()
                .id(bookDTO.getId())
                .title(bookDTO.getTitle())
                .author(this.authorMapper.toEntity(bookDTO.getAuthor()))
                .isbn(bookDTO.getIsbn())
                .publicationDate(bookDTO.getPublicationDate())
                .available(bookDTO.isAvailable())
                .build();
    }

}
