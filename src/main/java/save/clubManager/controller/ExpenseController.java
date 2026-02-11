package save.clubManager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import save.clubManager.domain.Dues;
import save.clubManager.domain.Expense;
import save.clubManager.repository.ExpenseRepository;
import save.clubManager.service.ExpenseService;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExpenseController {

    private final ExpenseService expenseService;
    private final ExpenseRepository expenseRepository;

    @Autowired
    public ExpenseController(ExpenseService expenseService, ExpenseRepository expenseRepository) {
        this.expenseService = expenseService;
        this.expenseRepository = expenseRepository;
    }

    @GetMapping("/expenses/new")
    public String createForm(Model model) {
        model.addAttribute("expenseForm", new ExpenseForm());
        return "expense/createExpenseForm";
    }

    @PostMapping("/expenses/new")
    public String create(ExpenseForm form) {
        Expense expense = new Expense();
        expense.setCategory(form.getCategory());
        expense.setAmount(form.getAmount());
        expense.setExpenseDate(form.getExpenseDate());
        expense.setDetail(form.getDetail());
        expense.setReceiptUrl(form.getReceiptUrl());

        expenseService.saveExpense(expense);
        return "redirect:/";
    }

    @GetMapping("/expenses")
    public String list(
            @RequestParam(value = "detail", required = false) String detail,
            // @DateTimeFormat을 추가하여 문자열 날짜를 LocalDate로 안전하게 받습니다.
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Model model) {

        List<Expense> expenses;

        // 팩트 체크: 입력값의 존재 여부를 명확히 판단합니다.
        boolean hasDetail = (detail != null && !detail.trim().isEmpty());
        boolean hasDate = (date != null);

        if (hasDetail && hasDate) {
            // 1. 상세내용 + 날짜 동시 검색
            expenses = expenseRepository.findByDetailContainingAndExpenseDateAfter(detail, date);
        } else if (hasDetail) {
            // 2. 상세내용만 검색
            expenses = expenseRepository.findByDetailContaining(detail);
        } else if (hasDate) {
            // 3. 날짜만 검색
            expenses = expenseRepository.findByExpenseDateAfter(date);
        } else {
            // 4. 검색 조건이 없으면 전체 리스트 반환
            expenses = expenseRepository.findAll();
        }
        model.addAttribute("expenses", expenses);
        model.addAttribute("isResultEmpty", expenses.isEmpty());
        return "expense/expenseList";
    }

    // 삭제 기능 추가
    @PostMapping("/expenses/{expenseId}/delete")
    public String deleteExpense(@PathVariable("expenseId") Long expenseId) {
        expenseService.deleteExpense(expenseId);
        return "redirect:/expenses";
    }

    // 수정 폼 조회
    @GetMapping("/expenses/{expenseId}/edit") // 경로 수정
    public String updateExpenseForm(@PathVariable("expenseId") Long expenseId, Model model) {
        Expense expense = expenseService.findOne(expenseId); // 지출 객체 조회

        ExpenseForm form = new ExpenseForm();
        // 롬복 미사용이므로 수동으로 데이터 세팅
        form.setCategory(expense.getCategory());
        form.setAmount(expense.getAmount());
        form.setExpenseDate(expense.getExpenseDate());
        form.setDetail(expense.getDetail());
        form.setReceiptUrl(expense.getReceiptUrl());

        model.addAttribute("form", form);
        model.addAttribute("expenseId", expenseId);
        return "expense/updateExpenseForm"; // 지출 수정 폼으로 연결
    }

    // 수정 실행
    @PostMapping("/expenses/{expenseId}/edit") // 경로 수정
    public String updateExpense(@PathVariable("expenseId") Long expenseId, @ModelAttribute("form") ExpenseForm form) {
        expenseService.updateExpense(
                expenseId,
                form.getCategory(),
                form.getAmount(),
                form.getExpenseDate(),
                form.getDetail(),
                form.getReceiptUrl()
        );
        return "redirect:/expenses"; // 지출 목록으로 리다이렉트
    }
}