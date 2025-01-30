package com.example.booktracker.book_tracker_service.repository;

import com.example.booktracker.book_tracker_service.entity.LoanEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoanRepository extends JpaRepository<LoanEntity, Long> {
    //List<LoanEntity> findByBookId(Long bookId);
    Page<LoanEntity> findByBookId(Long bookId, Pageable pageable);
    long countByStatus(boolean status);
}
