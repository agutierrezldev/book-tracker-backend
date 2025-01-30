package com.example.booktracker.book_tracker_service.controller;

import com.example.booktracker.book_tracker_service.dto.dashboard.DashboardDTO;
import com.example.booktracker.book_tracker_service.service.impl.IDashboardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
@RequiredArgsConstructor
@Tag(name = "Dashboard Controller", description = "API para obtener estadísticas generales del sistema")
public class DashboardController {

    private final IDashboardService dashboardService;

    @GetMapping
    @Operation(summary = "Obtener datos del dashboard", description = "Retorna información consolidada sobre préstamos, libros y usuarios en el sistema.")
    public ResponseEntity<DashboardDTO> getDashboard() {
        return ResponseEntity.ok(dashboardService.getDashboardData());
    }
}
