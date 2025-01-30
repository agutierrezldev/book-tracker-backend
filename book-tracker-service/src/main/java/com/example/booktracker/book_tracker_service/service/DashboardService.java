package com.example.booktracker.book_tracker_service.service;

import com.example.booktracker.book_tracker_service.dto.dashboard.DashboardDTO;
import com.example.booktracker.book_tracker_service.repository.AuthorRepository;
import com.example.booktracker.book_tracker_service.repository.BookRepository;
import com.example.booktracker.book_tracker_service.repository.LoanRepository;
import com.example.booktracker.book_tracker_service.service.impl.IDashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DashboardService implements IDashboardService {

    private final BookRepository bookRepository;
    private final LoanRepository loanRepository;
    private final AuthorRepository authorRepository;

    @Override
    public DashboardDTO getDashboardData() {
        DashboardDTO dashboardDTO = new DashboardDTO();

        dashboardDTO.setTotalBooks(this.bookRepository.count());
        dashboardDTO.setTotalAuthors(this.authorRepository.count());
        dashboardDTO.setTotalLoans(this.loanRepository.count());
        dashboardDTO.setActiveLoans(this.loanRepository.countByStatus(true));
        dashboardDTO.setFinishedLoans(this.loanRepository.countByStatus(false));

        return dashboardDTO;
    }
}
