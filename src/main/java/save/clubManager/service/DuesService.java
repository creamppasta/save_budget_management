package save.clubManager.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import save.clubManager.domain.Dues;
import save.clubManager.domain.Member;
import save.clubManager.repository.DuesRepository;
import save.clubManager.repository.MemberRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class DuesService {

    private final DuesRepository duesRepository;
    private final MemberRepository memberRepository;

    public DuesService(DuesRepository duesRepository, MemberRepository memberRepository) {
        this.duesRepository = duesRepository;
        this.memberRepository = memberRepository;
    }

    /** 회비 납부 등록 */
    @Transactional
    public Long saveDues(Long memberId, Dues dues) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("회원이 없습니다."));
        dues.setMember(member); // 이 부분이 반드시 있어야 합니다!
        return duesRepository.save(dues).getId();
    }

    /**
     * 납부 내역 수정
     */
    @Transactional
    public void updateDues(Long duesId, int amount, String semester, LocalDate paymentDate, String note) {
        Dues dues = duesRepository.findById(duesId)
                .orElseThrow(() -> new IllegalStateException("내역이 존재하지 않습니다."));

        // 변경 감지(Dirty Checking)에 의해 트랜잭션 종료 시 자동 Update 쿼리 실행
        dues.setAmount(amount);
        dues.setSemester(semester);
        dues.setPaymentDate(paymentDate);
        dues.setNote(note);
    }

    /**
     * 납부 내역 삭제
     */
    @Transactional
    public void deleteDues(Long duesId) {
        duesRepository.deleteById(duesId);
    }

    /** 모든 납부 내역 조회 */
    public List<Dues> findAllDues() {
        return duesRepository.findAll();
    }

    /**
     * 특정 납부 내역 단건 조회
     */
    public Dues findOne(Long duesId) {
        return duesRepository.findById(duesId)
                .orElseThrow(() -> new IllegalStateException("해당 납부 내역을 찾을 수 없습니다."));
    }
}