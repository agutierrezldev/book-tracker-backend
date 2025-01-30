package com.example.booktracker.book_tracker_service.dto.author;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthorDTO {
    private Long id;
    private String name;
    private String nationality;
    private LocalDate birthDate;
}
