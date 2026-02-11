package save.clubManager.service;

import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import save.clubManager.repository.DuesRepository;
import save.clubManager.repository.ExpenseRepository;

@Service
@RequiredArgsConstructor
public class FinanceService {
    private final DuesRepository duesRepository;
    private final ExpenseRepository expenseRepository;

    public int getBalance() {
        Integer totalDues = duesRepository.getTotalDues();
        Integer totalExpenses = expenseRepository.getTotalExpenses();

        int dues = (totalDues != null) ? totalDues : 0;
        int expenses = (totalExpenses != null) ? totalExpenses : 0;

        return dues - expenses;
    }
}