package save.clubManager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import save.clubManager.domain.Expense;
import save.clubManager.repository.ExpenseRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExpenseService {

    private final ExpenseRepository expenseRepository;

    // 생성자 주입 (Lombok 미사용)
    public ExpenseService(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    /**
     * 회비 지출 등록
     */
    @Transactional
    public Long saveExpense(Expense expense) {
        expenseRepository.save(expense);
        return expense.getId();
    }

    /**
     * 지출 내역 수정
     */
    @Transactional
    public void updateExpense(Long expenseId, String category, int amount, LocalDate expenseDate, String detail, String receiptUrl) {
        Expense expense = expenseRepository.findById(expenseId)
                .orElseThrow(() -> new IllegalStateException("내역이 존재하지 않습니다."));

        expense.setCategory(category);
        expense.setAmount(amount);
        expense.setExpenseDate(expenseDate);
        expense.setDetail(detail);
        expense.setReceiptUrl(receiptUrl);
    }

    /**
     * 지출 내역 삭제
     */
    @Transactional
    public void deleteExpense(Long expenseId) {
        expenseRepository.deleteById(expenseId);
    }

    /**
     * 지출 내역 전체 조회
     */
    public List<Expense> findAllExpenses() {
        return expenseRepository.findAll();
    }

    /**
     * 특정 지출 내역 단건 조회
     */
    public Expense findOne(Long expenseId) {
        return expenseRepository.findById(expenseId)
                .orElseThrow(() -> new IllegalStateException("지출 내역을 찾을 수 없습니다."));
    }
}