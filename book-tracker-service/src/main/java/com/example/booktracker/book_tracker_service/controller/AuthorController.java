package com.example.booktracker.book_tracker_service.controller;

import com.example.booktracker.book_tracker_service.dto.author.AuthorDTO;
import com.example.booktracker.book_tracker_service.service.impl.IAuthorService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/authors")
@Tag(name = "Authors", description = "Endpoints para gestionar autores")
public class AuthorController {

    private final IAuthorService authorService;

    @PostMapping
    @Operation(summary = "Crear un nuevo autor", description = "Crea y devuelve un nuevo autor basado en los datos proporcionados.")
    public ResponseEntity<AuthorDTO> create(@RequestBody AuthorDTO authorDTO) {
        AuthorDTO createdAuthor = this.authorService.create(authorDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAuthor);
    }

    @GetMapping("/pageable")
    @Operation(summary = "Obtener lista paginada de autores", description = "Devuelve una lista de autores paginada basada en los parámetros de paginación.")
    public Page<AuthorDTO> getAllPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return authorService.getAllPageable(page, size);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los autores", description = "Devuelve una lista de todos los autores registrados en el sistema.")
    public ResponseEntity<List<AuthorDTO>> getAll() {
        List<AuthorDTO> authors = this.authorService.getAll();
        return ResponseEntity.ok(authors);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un autor por ID", description = "Devuelve la información de un autor específico basado en su ID.")
    public ResponseEntity<AuthorDTO> getById(@PathVariable Long id) {
        return this.authorService.getById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un autor", description = "Actualiza los datos de un autor existente basado en su ID.")
    public ResponseEntity<AuthorDTO> update(@PathVariable Long id, @RequestBody AuthorDTO authorDTO) {
        return this.authorService.update(id, authorDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un autor", description = "Elimina un autor existente basado en su ID.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (authorService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
