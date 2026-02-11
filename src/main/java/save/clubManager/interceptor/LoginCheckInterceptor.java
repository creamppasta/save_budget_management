package save.clubManager.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();

        // 팩트 체크: 세션에 로그인 정보가 없으면 로그인 페이지로 튕겨버림
        if (session == null || session.getAttribute("loginMember") == null) {
            response.sendRedirect("/login");
            return false; // 컨트롤러로 가지 마!
        }
        return true; // 로그인 했으면 통과!
    }
}