package com.example.booktracker.book_tracker_service.mapper.loan;

import com.example.booktracker.book_tracker_service.dto.loan.LoanDTO;
import com.example.booktracker.book_tracker_service.entity.LoanEntity;
import com.example.booktracker.book_tracker_service.mapper.book.BookMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class LoanMapper {

    private final BookMapper bookMapper;

    public LoanDTO toDTO(LoanEntity loanEntity) {
        if (loanEntity == null) {
            return null;
        }
        LoanDTO loanDTO = new LoanDTO();
        loanDTO.setId(loanEntity.getId());
        loanDTO.setBook(this.bookMapper.toDTO(loanEntity.getBook()));
        loanDTO.setLoanDate(loanEntity.getLoanDate());
        loanDTO.setReturnDate(loanEntity.getReturnDate());
        loanDTO.setStatus(loanEntity.getStatus());
        return loanDTO;
    }

    public LoanEntity toEntity(LoanDTO loanDTO) {
        LoanEntity loan = new LoanEntity();
        loan.setId(loanDTO.getId());
        loan.setLoanDate(loanDTO.getLoanDate());
        loan.setReturnDate(loanDTO.getReturnDate());
        loan.setStatus(loanDTO.isStatus());
        loan.setBook(this.bookMapper.toEntity(loanDTO.getBook()));

        return loan;
    }

    public List<LoanDTO> toDTOList(List<LoanEntity> loanEntities) {
        return loanEntities.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

}
