package save.clubManager.domain;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Dues {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private int amount;          // 납부 금액
    private String semester;     // 납부 학기
    private LocalDate paymentDate; // 납부 날짜
    private String note;         // 기타 사항

    public Dues() {} // 기본 생성자

    public Dues(Member member, int amount, String semester, LocalDate paymentDate, String note) {
        this.member = member;
        this.amount = amount;
        this.semester = semester;
        this.paymentDate = paymentDate;
        this.note = note;
    }

    // Getter & Setter
    public Long getId() { return id; }
    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }
    public int getAmount() { return amount; }
    public void setAmount(int amount) { this.amount = amount; }
    public String getSemester() { return semester; }
    public void setSemester(String semester) { this.semester = semester; }
    public LocalDate getPaymentDate() { return paymentDate; }
    public void setPaymentDate(LocalDate paymentDate) { this.paymentDate = paymentDate; }
    public String getNote() { return note; }
    public void setNote(String note) { this.note = note; }
}