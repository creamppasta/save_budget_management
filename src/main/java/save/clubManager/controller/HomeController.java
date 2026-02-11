package save.clubManager.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import save.clubManager.service.FinanceService;

@Controller
@RequiredArgsConstructor // FinanceService를 자동으로 연결해줍니다.
public class HomeController {

    private final FinanceService financeService;

    @RequestMapping("/") /** 겟매핑, 리퀘스트매핑 상관없이 동시에 같은 주소를 향하고 있으면 경쟁이 붙어서 알파벳순으로 선점함
                             그래서 아까 MemberController가 HomeController보다 우선순위 였던 것 */
    public String home(Model model) {
        // FinanceService에서 계산된 잔액을 가져와서 "balance"라는 이름으로 전달
        int balance = financeService.getBalance();

        model.addAttribute("balance", balance);
        return "home"; // 또는 "index" 등 사용 중인 홈 HTML 파일명
    }
}
