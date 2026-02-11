package save.clubManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import save.clubManager.domain.Expense;
import save.clubManager.domain.Member;

import java.util.List;

// JpaRepository<다룰 객체, PK타입>
// 이렇게만 해두면 저장(save), 조회(findById), 전체조회(findAll) 코드를 스프링이 자동으로 만들어줍니다.
public interface MemberRepository extends JpaRepository<Member, Long> {
    @Query("select m from Member m where m.id not in " +
            "(select d.member.id from Dues d where d.semester = :semester)")
    List<Member> findUnpaidMembers(@Param("semester") String semester);

    // 이름 또는 학과에 검색어가 포함된 리스트 조회
    List<Member> findByNameContainingOrDepartmentContaining(String name, String department);
}
