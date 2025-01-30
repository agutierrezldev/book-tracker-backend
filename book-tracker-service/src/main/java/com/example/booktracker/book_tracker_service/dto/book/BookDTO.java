package com.example.booktracker.book_tracker_service.dto.book;

import com.example.booktracker.book_tracker_service.dto.author.AuthorDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookDTO {

    private Long id;
    private String title;
    private AuthorDTO author;
    private String isbn;
    private LocalDate publicationDate;
    private boolean available;
}
