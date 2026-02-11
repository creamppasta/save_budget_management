package save.clubManager.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Expense {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String category;    // 카테고리
    private int amount;         // 지출 금액
    private LocalDate expenseDate; // 지출 날짜
    private String detail;      // 세부사항
    private String receiptUrl;  // 증빙서류 (이미지 경로 등)


    public Expense() {}

    public Expense(String category, int amount, LocalDate expenseDate, String receiptUrl, String detail) {
        this.category = category;
        this.amount = amount;
        this.expenseDate = expenseDate;
        this.detail = detail;
        this.receiptUrl = receiptUrl;

    }

    // Getter & Setter 직접 작성
    public Long getId() { return id; }
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public LocalDate getExpenseDate() { return expenseDate; }
    public void setExpenseDate(LocalDate expenseDate) { this.expenseDate = expenseDate; }
    public String getDetail() { return detail; }
    public void setDetail(String detail) { this.detail = detail; }
    public String getReceiptUrl() { return receiptUrl; }
    public void setReceiptUrl(String receiptUrl) { this.receiptUrl = receiptUrl; }

}