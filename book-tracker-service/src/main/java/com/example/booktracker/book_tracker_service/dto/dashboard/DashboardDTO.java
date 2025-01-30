package com.example.booktracker.book_tracker_service.dto.dashboard;

import lombok.Data;

@Data
public class DashboardDTO {
    private long totalBooks;
    private long totalAuthors;
    private long totalLoans;
    private long activeLoans;
    private long finishedLoans;
}
