package save.clubManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import save.clubManager.domain.Expense;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    @Query("select sum(e.amount) from Expense e")
    Integer getTotalExpenses();

    // 엔티티 필드명이 detail일 때
    List<Expense> findByDetailContaining(String detail);
    List<Expense> findByDetailContainingAndExpenseDateAfter(String detail, LocalDate date);
    List<Expense> findByExpenseDateAfter(LocalDate date);
}