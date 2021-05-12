package ac.kr.smu.Interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        boolean chk = session.getAttribute("userSession") != null;

        if(!chk) // userSession이 없다면
            response.sendRedirect("/"); // 로그인 페이지로

        return chk;
    }
}
