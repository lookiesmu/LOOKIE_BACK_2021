package ac.kr.smu.interceptor;

import ac.kr.smu.service.PostService;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class PostInterceptor implements HandlerInterceptor {
    private PostService postService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();// ex) /post/1
        HttpSession session = request.getSession();
        /*
            우선 순위 때문인지 의존성 주입이 되지 않아 postService가 null이 된다.
            따라서 직접 설정에서 PostService 클래스의 빈을 가져와 넣어준다.
        */
        if(postService==null){
            WebApplicationContext context =
                    WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
            postService = context.getBean(PostService.class);
        }

        if (request.getMethod().equals("PUT") || request.getMethod().equals("DELETE") ||
                (request.getMethod().equals("GET") && request.getParameter("isModify")!=null &&request.getParameter("isModify").equals("true"))) {
            String email = (String) session.getAttribute("userSession");
            String tmp= new String();
            /*
                여러 방법이 있겠지만 가장 간단하게 마지막 / 뒤에 postId가 있으므로
                그 위치부터 char를 받아와서 숫자일 때까지 tmp에 더하여 형변환하면
                간단히 postId를 uri로부터 얻을 수 있다.
            */
            for(int i=uri.lastIndexOf("/")+1; i<uri.length(); i++){
                if(uri.charAt(i)>='0' && uri.charAt(i)<='9')
                    tmp+=uri.charAt(i);
            }
            int postId=Integer.valueOf(tmp);
            boolean result = postService.findById(postId).getUser().getEmail().equals(email);

            if(!result)
                response.sendRedirect("/board");

            return result;
        }

        return true;
    }
}