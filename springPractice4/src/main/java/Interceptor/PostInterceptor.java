package Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import service.PostService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Slf4j
public class PostInterceptor implements HandlerInterceptor {
    private PostService postService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        HttpSession session = request.getSession();

        if (postService == null) {
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
            postService = context.getBean(PostService.class);
        }


        if (request.getMethod().equals("PUT") || request.getMethod().equals("DELETE") ||
                (request.getMethod().equals("GET") && request.getParameter("isModify") != null && request.getParameter("isModify").equals("true"))) {
            String email = (String) session.getAttribute("userSession");
            String tmp = new String();

            // 주소로부터 postId 얻음
            for (int i = uri.lastIndexOf("/") + 1; i < uri.length(); i++) {
                if (uri.charAt(i) >= '0' && uri.charAt(i) <= '9')
                    tmp += uri.charAt(i);
            }
            int postId = Integer.valueOf(tmp);

            //세션에서 얻은 email과 같다면 true 반환
            boolean result = postService.findById(postId).getUser().getEmail().equals(email);

            //log.info("작성자 맞음? "+result);

            //email 같지 않으면 redirect
            if (!result)
                response.sendRedirect("/board");
            return result;

        }
        return true;
    }

}


