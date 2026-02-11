package save.clubManager.controller;

import java.time.LocalDate;

public class ExpenseForm {
    private String category;
    private int amount;
    private LocalDate expenseDate;
    private String detail;
    private String receiptUrl;

    // Getter & Setter
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