package save.clubManager.controller;

import org.springframework.web.bind.annotation.RequestParam;
import save.clubManager.domain.Member;
import save.clubManager.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PathVariable; // ★ 이 친구가 핵심입니다!

import java.util.List;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

//    // 홈 화면
//    @GetMapping("/")
//    public String home() {
//        return "home";
//    }

    // 회원 가입 폼 이동
    @GetMapping("/members/new")
    public String createForm() {
        return "members/createMemberForm";
    }

    // 회원 가입 실행
    @PostMapping("/members/new")
    public String create(MemberForm form) {
        Member member = new Member(
                form.getName(),
                form.getStudentId(),
                form.getDepartment(),
                form.getPhoneNumber(),
                form.getEtc()
        );

        memberService.join(member);
        return "redirect:/";
    }

    // 회원 목록 조회
    @GetMapping("/members")
    public String list(@RequestParam(value = "searchText", required = false) String searchText, Model model) {
        List<Member> members;
        if (searchText != null && !searchText.isEmpty()) {
            // 이름과 학과 모두에서 검색 시도
            members = memberService.searchMembers(searchText);
        } else {
            members = memberService.findMembers();
        }
        model.addAttribute("members", members);
        model.addAttribute("isResultEmpty", members.isEmpty());
        return "members/memberList";
    }

    // ★ 회원 수정 폼 이동 (여기서 에러가 났었습니다)
    @GetMapping("/members/{memberId}/edit")
    public String updateMemberForm(@PathVariable("memberId") Long memberId, Model model) {
        Member member = memberService.findOne(memberId); // 서비스에 findOne이 있어야 함

        MemberForm form = new MemberForm();
        form.setId(member.getId());
        form.setName(member.getName());
        form.setStudentId(member.getStudentId());
        form.setDepartment(member.getDepartment());
        form.setPhoneNumber(member.getPhoneNumber());
        form.setEtc(member.getEtc());

        model.addAttribute("form", form);
        return "members/updateMemberForm";
    }

    // ★ 회원 수정 실행
    @PostMapping("/members/{memberId}/edit")
    public String updateMember(@PathVariable("memberId") Long memberId, MemberForm form) {
        memberService.update(memberId, form);
        return "redirect:/members";
    }

    // ★ 회원 삭제 실행
    @GetMapping("/members/{memberId}/delete")
    public String deleteMember(@PathVariable("memberId") Long memberId) {
        memberService.delete(memberId);
        return "redirect:/members";
    }
}