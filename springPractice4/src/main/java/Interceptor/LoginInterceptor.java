package Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session  = request.getSession();
        /*
        log.info(request.getRequestURI());
        log.info("null"+(session.getAttribute("userSession")==null));
         */
        boolean chk=session.getAttribute("userSession") != null;

        //유저 세션 없으면 돌아감
        if(!chk)
            response.sendRedirect("/");

        return chk;
    }
}
