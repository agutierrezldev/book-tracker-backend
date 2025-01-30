package com.example.booktracker.book_tracker_service.controller;

import com.example.booktracker.book_tracker_service.dto.loan.LoanDTO;

import com.example.booktracker.book_tracker_service.service.impl.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequiredArgsConstructor
@RequestMapping("/loans")
@Tag(name = "Loan Controller", description = "API para la gestión de préstamos de libros")
public class LoanController {

    private final ILoanService loanService;

    @GetMapping
    @Operation(summary = "Obtener todos los préstamos paginados", description = "Permite obtener una lista de préstamos de libros con paginación y filtro opcional por ID de libro.")
    public ResponseEntity<Page<LoanDTO>> getAllLoans(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(required = false) Long bookId
            ) {

        PageRequest pageRequest = PageRequest.of(page, size);
        Page<LoanDTO> loanPage = loanService.getPaginatedLoans(bookId,pageRequest);
        return ResponseEntity.ok(loanPage);
    }

    @PostMapping
    @Operation(summary = "Crear un préstamo", description = "Registra un nuevo préstamo de libro.")
    public ResponseEntity<LoanDTO> create(@RequestBody LoanDTO loanDTO) {
        LoanDTO createdLoan = this.loanService.create(loanDTO);
        return ResponseEntity.ok(createdLoan);
    }

//    @GetMapping("/book/{bookId}")
//    public ResponseEntity<List<LoanDTO>> getLoansByBook(@PathVariable Long bookId) {
//        List<LoanDTO> loans = this.loanService.getLoansByBook(bookId);
//        return ResponseEntity.ok(loans);
//    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un préstamo por ID", description = "Busca un préstamo por su identificador único.")
    public ResponseEntity<LoanDTO> getLoan(@PathVariable Long id) {
        LoanDTO loan = loanService.getById(id);
        return loan != null ? ResponseEntity.ok(loan) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualizar un préstamo", description = "Modifica la información de un préstamo existente.")
    public ResponseEntity<LoanDTO> update(@PathVariable Long id, @RequestBody LoanDTO loanDTO) {
        LoanDTO updatedLoan = this.loanService.update(id, loanDTO);
        return updatedLoan != null ? ResponseEntity.ok(updatedLoan) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un préstamo", description = "Elimina un préstamo por su identificador.")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.loanService.delete(id);
        return ResponseEntity.noContent().build();
    }

//    @GetMapping("/available/{bookId}")
//    public ResponseEntity<Boolean> isBookAvailable(@PathVariable Long bookId) {
//        boolean available = this.loanService.isBookAvailable(bookId);
//        return ResponseEntity.ok(available);
//    }
}
