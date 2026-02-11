package save.clubManager;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import save.clubManager.interceptor.LoginCheckInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1) // 우선순위 1번
                .addPathPatterns("/**") // 모든 주소에 다 적용해라!
                .excludePathPatterns("/login", "/logout", "/css/**", "/*.ico", "/error"); // 로그인, 정적 리소스는 제외!
    }
}