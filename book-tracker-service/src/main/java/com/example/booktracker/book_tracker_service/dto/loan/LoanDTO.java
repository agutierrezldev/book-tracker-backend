package com.example.booktracker.book_tracker_service.dto.loan;

import com.example.booktracker.book_tracker_service.dto.book.BookDTO;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoanDTO {
    private Long id;
    private BookDTO book;
    private LocalDate loanDate;
    private LocalDate returnDate;
    private boolean status;
}
