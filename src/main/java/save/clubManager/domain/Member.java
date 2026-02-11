package save.clubManager.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;              // 시스템이 관리하는 고유 ID

    private String name;          // 이름
    private String studentId;     // 학번 (예: 20231234)
    private String department;    // 학과 (예: 컴공)
    private String phoneNumber;   // 전화번호
    private LocalDate joinDate;   // 가입 날짜 (2026-02-09)
    private String etc;           // 기타사항

    // 기본 생성자
    protected Member() {}

    // 데이터 넣기 편하게 만든 생성자
    public Member(String name, String studentId, String department, String phoneNumber, String etc) {
        this.name = name;
        this.studentId = studentId;
        this.department = department;
        this.phoneNumber = phoneNumber;
        this.joinDate = LocalDate.now(); // 생성되자마자 오늘 날짜 자동 입력
        this.etc = etc;
    }
}
