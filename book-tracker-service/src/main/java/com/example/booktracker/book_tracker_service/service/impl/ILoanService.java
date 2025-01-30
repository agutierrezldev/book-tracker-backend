package com.example.booktracker.book_tracker_service.service.impl;

import com.example.booktracker.book_tracker_service.dto.loan.LoanDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface ILoanService {

//    List<LoanDTO> getLoansByBook(Long bookId);

    Page<LoanDTO> getPaginatedLoans(Long bookid, PageRequest pageRequest);

    LoanDTO getById(Long id);

    LoanDTO create(LoanDTO loanDTO);

    LoanDTO update(Long id, LoanDTO loanDTO);

    Boolean delete(Long id);

//    Boolean isBookAvailable(Long bookId);
}
