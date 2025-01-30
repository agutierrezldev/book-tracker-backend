package com.example.booktracker.book_tracker_service.service;

import com.example.booktracker.book_tracker_service.dto.loan.LoanDTO;
import com.example.booktracker.book_tracker_service.entity.LoanEntity;
import com.example.booktracker.book_tracker_service.mapper.loan.LoanMapper;
import com.example.booktracker.book_tracker_service.repository.LoanRepository;
import com.example.booktracker.book_tracker_service.service.impl.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoanService implements ILoanService {

    private final LoanRepository loanRepository;
    private final LoanMapper loanMapper;

    public LoanDTO create(LoanDTO loanDTO) {
        LoanEntity loanEntity = this.loanMapper.toEntity(loanDTO);
        LoanEntity savedLoan = this.loanRepository.save(loanEntity);
        return this.loanMapper.toDTO(savedLoan);
    }

//    public List<LoanDTO> getLoansByBook(Long bookId) {
//        List<LoanEntity> loans = this.loanRepository.findByBookId(bookId);
//        return loanMapper.toDTOList(loans);
//    }

    public LoanDTO getById(Long id) {
        Optional<LoanEntity> loan = this.loanRepository.findById(id);
        return loan.map(this.loanMapper::toDTO).orElse(null);
    }

    public LoanDTO update(Long id, LoanDTO loanDTO) {
        Optional<LoanEntity> existingLoan = this.loanRepository.findById(id);
        if (existingLoan.isPresent()) {
            LoanEntity loanEntity = existingLoan.get();
            loanEntity.setLoanDate(loanDTO.getLoanDate());
            loanEntity.setReturnDate(loanDTO.getReturnDate());
            loanEntity.setStatus(loanDTO.isStatus());
            loanRepository.save(loanEntity);
            return loanMapper.toDTO(loanEntity);
        }
        return null;
    }

    public Boolean delete(Long id) {
        return this.loanRepository.findById(id)
                .map(loan -> {
                    this.loanRepository.delete(loan);
                    return true;
                })
                .orElse(false);
    }

//    public Boolean isBookAvailable(Long bookId) {
//        List<LoanEntity> loans = this.loanRepository.findByBookId(bookId);
//        return loans.stream().noneMatch(LoanEntity::getStatus);
//    }

    public Page<LoanDTO> getPaginatedLoans(Long id, PageRequest pageRequest) {
        Page<LoanEntity> loanPage;

        if (id != null) {
            loanPage = this.loanRepository.findByBookId(id, pageRequest);
        } else {
            loanPage = this.loanRepository.findAll(pageRequest);
        }
        return loanPage.map(loanMapper::toDTO);
    }
}
