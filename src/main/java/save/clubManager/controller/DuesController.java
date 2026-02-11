package save.clubManager.controller;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import save.clubManager.domain.Dues;

import save.clubManager.domain.Member;
import save.clubManager.repository.DuesRepository;
import save.clubManager.service.MemberService;
import save.clubManager.service.DuesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.time.LocalDate;
import java.util.List;
@Controller
public class DuesController {

    private final DuesService duesService;
    private final MemberService memberService; // 회원 목록 조회를 위해 필요
    private final DuesRepository duesRepository;

    public DuesController(DuesService duesService, MemberService memberService, DuesRepository duesRepository) {
        this.duesService = duesService;
        this.memberService = memberService;
        this.duesRepository = duesRepository;
    }

    @GetMapping("/dues/new")
    public String createForm(Model model) {
        List<Member> members = memberService.findMembers();
        model.addAttribute("members", members); // 회원 목록을 드롭다운에 전달
        return "dues/createDuesForm";
    }

    @PostMapping("/dues/new")
    public String create(@RequestParam("memberId") Long memberId, DuesForm form) {
        // Dues 객체 생성 로직 (Lombok 미사용이므로 수동 세팅)
        Dues dues = new Dues();

        dues.setAmount(form.getAmount());
        dues.setSemester(form.getSemester());
        dues.setPaymentDate(form.getPaymentDate());
        dues.setNote(form.getNote());

        duesService.saveDues(memberId, dues);
        return "redirect:/";
    }

    @GetMapping("/dues")
    public String list(
            @RequestParam(value = "name", required = false) String name,
            // @DateTimeFormat을 붙여서 문자열을 LocalDate로 자동 변환합니다.
            @RequestParam(value = "date", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date,
            Model model) {

        List<Dues> duesList;

        // 팩트 체크: 이름과 날짜 입력 여부를 명확히 판단 (공백 제거 포함)
        boolean hasName = (name != null && !name.trim().isEmpty());
        boolean hasDate = (date != null);

        if (hasName && hasDate) {
            // 1. 이름 + 날짜 동시 검색 (여기서 에러가 났다면 Repository 메서드 파라미터 타입 확인 필요)
            duesList = duesRepository.findByMemberNameContainingAndPaymentDateAfter(name, date);
        } else if (hasName) {
            // 2. 이름만 검색
            duesList = duesRepository.findByMemberNameContaining(name);
        } else if (hasDate) {
            // 3. ★날짜만 검색 (이 부분이 누락되었거나 아래로 밀려있으면 전체 조회가 됨)
            // Repository에 findByPaymentDateAfter 메서드를 추가해야 합니다.
            duesList = duesRepository.findByPaymentDateAfter(date);
        } else {
            // 4. 검색어 없을 때 전체 조회
            duesList = duesRepository.findAll();
        }

        model.addAttribute("duesList", duesList);
        model.addAttribute("isResultEmpty", duesList.isEmpty());

        return "dues/duesList";
    }

    // 삭제 기능 추가
    @PostMapping("/dues/{duesId}/delete")
    public String deleteDues(@PathVariable("duesId") Long duesId) {
        duesService.deleteDues(duesId);
        return "redirect:/dues";
    }

    // 수정 폼 조회
    @GetMapping("/dues/{duesId}/edit")
    public String updateDuesForm(@PathVariable("duesId") Long duesId, Model model) {
        Dues dues = duesService.findOne(duesId);

        DuesForm form = new DuesForm();
        form.setMemberName(dues.getMember().getName()); // 이름 데이터 세팅
        form.setAmount(dues.getAmount());
        form.setSemester(dues.getSemester());
        form.setPaymentDate(dues.getPaymentDate());
        form.setNote(dues.getNote());

        model.addAttribute("form", form);
        model.addAttribute("duesId", duesId);
        return "dues/updateDuesForm";
    }

    // 수정 실행
    @PostMapping("/dues/{duesId}/edit")
    public String updateDues(@PathVariable("duesId") Long duesId, @ModelAttribute("form") DuesForm form) {
        duesService.updateDues(duesId, form.getAmount(), form.getSemester(), form.getPaymentDate(), form.getNote());
        return "redirect:/dues";
    }

    // 학기별 회비 미납자 조회
    @GetMapping("/dues/unpaid")
    public String unpaidList(@RequestParam(value = "semester", required = false) String semester, Model model) {
        if (semester != null && !semester.isEmpty()) {
            List<Member> unpaidMembers = memberService.getUnpaidMembers(semester);
            model.addAttribute("unpaidMembers", unpaidMembers);
            model.addAttribute("semester", semester);
        }
        return "dues/unpaidList";
    }
}