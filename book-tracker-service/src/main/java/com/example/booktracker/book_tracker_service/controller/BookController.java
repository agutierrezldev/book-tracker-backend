package com.example.booktracker.book_tracker_service.controller;

import com.example.booktracker.book_tracker_service.dto.book.BookDTO;
import com.example.booktracker.book_tracker_service.service.impl.IBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
@Tag(name = "Books", description = "Endpoints para gestionar libros")
public class BookController {

    private final IBookService bookService;

    @PostMapping
    @Operation(summary = "Crear un nuevo libro", description = "Crea y devuelve un nuevo libro basado en los datos proporcionados.")
    public ResponseEntity<BookDTO> create(@RequestBody BookDTO bookDTO) {
        BookDTO createdBook = this.bookService.create(bookDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBook);
    }

    @GetMapping("/pageable")
    @Operation(summary = "Obtener lista paginada de libros", description = "Devuelve una lista de libros paginada basada en los parámetros de paginación.")
    public Page<BookDTO> getAllPageable(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size
    ) {
        return this.bookService.getAllPageable(page, size);
    }

    @GetMapping
    @Operation(summary = "Obtener todos los libros", description = "Devuelve una lista de todos los libros registrados en el sistema.")
    public ResponseEntity<List<BookDTO>> getAll() {
        List<BookDTO> books = this.bookService.getAll();
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un libro por ID", description = "Devuelve la información de un libro específico basado en su ID.")
    public ResponseEntity<BookDTO> getById(@PathVariable Long id) {
        return this.bookService.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un libro", description = "Actualiza los datos de un libro existente basado en su ID.")
    public ResponseEntity<BookDTO> update(@PathVariable Long id, @RequestBody BookDTO bookDTO) {
        return this.bookService.update(id, bookDTO)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un libro", description = "Elimina un libro existente basado en su ID.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (this.bookService.delete(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
