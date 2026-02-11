package save.clubManager.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    // 팩트: 실무에서는 DB에 저장하지만, 현재는 연습용으로 컨트롤러에 상수로 박아둡니다.
    private final String USER_PW = "2002";  // 일반 사용자용
    private final String ADMIN_PW = "hhwin2026"; // 관리자용 (나만 아는 것)

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("password") String password, HttpSession session) {
        if (ADMIN_PW.equals(password)) {
            session.setAttribute("loginMember", "ADMIN"); // 관리자 권한 부여
            return "redirect:/";
        } else if (USER_PW.equals(password)) {
            session.setAttribute("loginMember", "USER"); // 일반 권한 부여
            return "redirect:/";
        }

        // 비밀번호 틀렸을 때
        return "redirect:/login?error=true";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate(); // 세션 날리기
        return "redirect:/login";
    }
}