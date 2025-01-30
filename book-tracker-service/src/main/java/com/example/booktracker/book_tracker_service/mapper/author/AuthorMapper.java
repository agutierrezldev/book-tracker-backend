package com.example.booktracker.book_tracker_service.mapper.author;

import com.example.booktracker.book_tracker_service.dto.author.AuthorDTO;
import com.example.booktracker.book_tracker_service.entity.AuthorEntity;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

    public AuthorDTO toDTO(AuthorEntity authorEntity) {
        return AuthorDTO.builder()
                .id(authorEntity.getId())
                .name(authorEntity.getName())
                .nationality(authorEntity.getNationality())
                .birthDate(authorEntity.getBirthDate())
                .build();
    }

    public AuthorEntity toEntity(AuthorDTO authorDTO) {
        return AuthorEntity.builder()
                .id(authorDTO.getId())
                .name(authorDTO.getName())
                .nationality(authorDTO.getNationality())
                .birthDate(authorDTO.getBirthDate())
                .build();
    }
}
