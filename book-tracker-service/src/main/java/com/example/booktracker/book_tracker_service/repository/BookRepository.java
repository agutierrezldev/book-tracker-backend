package com.example.booktracker.book_tracker_service.repository;

import com.example.booktracker.book_tracker_service.entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<BookEntity, Long> {

}

