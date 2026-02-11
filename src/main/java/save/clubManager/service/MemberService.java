package save.clubManager.service;

import org.springframework.beans.factory.annotation.Autowired;
import save.clubManager.controller.MemberForm;
import save.clubManager.domain.Member;
import save.clubManager.repository.MemberRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service; // (선택) @Service 어노테이션 사용 시 필요

import java.util.List;
import java.util.Optional;

@Service // 만약 컴포넌트 스캔 방식이면 주석 해제
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member) {
        memberRepository.save(member);
        return member.getId();
    }

    // 전체 회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // ★ 회원 수정 (변경 감지)
    public void update(Long id, MemberForm form) {
        Member member = memberRepository.findById(id).get();
        member.setId(form.getId());
        member.setName(form.getName());
        member.setStudentId(form.getStudentId());
        member.setDepartment(form.getDepartment());
        member.setPhoneNumber(form.getPhoneNumber());
        member.setEtc(form.getEtc());
    }

    // ★ 회원 삭제
    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    // ★ ID로 회원 하나 조회 (이게 없어서 에러 났음)
    public Member findOne(Long memberId) {
        return memberRepository.findById(memberId).get();
    }

    // 회비 미납자 조회
    public List<Member> getUnpaidMembers(String semester) {
        return memberRepository.findUnpaidMembers(semester);
    }

    /**
     * 이름 또는 학과로 회원 검색
     */
    public List<Member> searchMembers(String searchText) {
        // Repository에 미리 만들어둔 쿼리 메서드를 호출합니다.
        return memberRepository.findByNameContainingOrDepartmentContaining(searchText, searchText);
    }
}