package com.example.booktracker.book_tracker_service.service;

import com.example.booktracker.book_tracker_service.dto.author.AuthorDTO;
import com.example.booktracker.book_tracker_service.entity.AuthorEntity;
import com.example.booktracker.book_tracker_service.mapper.author.AuthorMapper;
import com.example.booktracker.book_tracker_service.repository.AuthorRepository;
import com.example.booktracker.book_tracker_service.service.impl.IAuthorService;
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
public class AuthorService implements IAuthorService {

    private final AuthorRepository authorRepository;
    private final AuthorMapper authorMapper;

    @Override
    public List<AuthorDTO> getAll() {
        List<AuthorEntity> authors = this.authorRepository.findAll();
        return authors.stream()
                .map(authorMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Page<AuthorDTO> getAllPageable(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(Sort.Direction.DESC, "createdAt"));
        Page<AuthorEntity> authors = this.authorRepository.findAll(pageable);
        return authors.map(authorMapper::toDTO);
    }

    @Override
    public Optional<AuthorDTO> getById(Long id) {
        return this.authorRepository.findById(id)
                .map(this.authorMapper::toDTO);
    }

    @Override
    public AuthorDTO create(AuthorDTO authorDTO) {
        AuthorEntity authorEntity = this.authorMapper.toEntity(authorDTO);
        AuthorEntity savedAuthor = this.authorRepository.save(authorEntity);
        return this.authorMapper.toDTO(savedAuthor);
    }

    @Override
    public Optional<AuthorDTO> update(Long id, AuthorDTO authorDTO) {
        return this.authorRepository.findById(id)
                .map(existingAuthor -> {
                    existingAuthor.setName(authorDTO.getName());
                    existingAuthor.setNationality(authorDTO.getNationality());
                    existingAuthor.setBirthDate(authorDTO.getBirthDate());
                    AuthorEntity updatedAuthor = authorRepository.save(existingAuthor);
                    return this.authorMapper.toDTO(updatedAuthor);
                });
    }

    @Override
    public Boolean delete(Long id) {
        return this.authorRepository.findById(id)
                .map(author -> {
                    this.authorRepository.delete(author);
                    return true;
                })
                .orElse(false);
    }
}
