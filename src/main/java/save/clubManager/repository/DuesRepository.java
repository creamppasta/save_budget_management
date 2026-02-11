package save.clubManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import save.clubManager.domain.Dues;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DuesRepository extends JpaRepository<Dues, Long> {
    @Query("select sum(d.amount) from Dues d")
    Integer getTotalDues(); // 데이터가 없으면 null을 반환하므로 Integer 사용

    // 이름 포함 + 특정 날짜 이후 데이터 검색
    List<Dues> findByMemberNameContainingAndPaymentDateAfter(String name, LocalDate date);

    // 기본적으로 이름으로만 검색하는 메서드 (이미 작성되어 있다면 활용)
    List<Dues> findByMemberNameContaining(String name);

    // 특정 날짜 이후로만 검색하는 메서드
    List<Dues> findByPaymentDateAfter(LocalDate date);
}