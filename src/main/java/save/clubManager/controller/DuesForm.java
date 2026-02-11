package save.clubManager.controller;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class DuesForm {
    private String memberName;
    private int amount;
    private String semester;
    @DateTimeFormat(pattern = "yyyy-MM-dd") // 이 어노테이션이 날짜 데이터를 정확히 잡아줍니다.
    private LocalDate paymentDate;
    private String note;

    // Getter & Setter
    public String getMemberName() { return memberName; }
    public void setMemberName(String memberName) { this.memberName = memberName; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}