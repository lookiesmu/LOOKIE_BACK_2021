package ac.kr.smu.ArgumentResolver;

import ac.kr.smu.service.UserService;
import ac.kr.smu.vo.UserVO;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpSession;

public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    private UserService userService;

    // 어떤 파라미터가 들어올 때 ArgumentResolver가 수행할지
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
        return methodParameter.getParameterType().equals(UserVO.class);
    }

    // 세션에서 email을 가져와 UserService를 이용하여 User 반환환
    @Override
    public Object resolveArgument(MethodParameter methodParameter, ModelAndViewContainer modelAndViewContainer, NativeWebRequest nativeWebRequest, WebDataBinderFactory webDataBinderFactory) throws Exception {
        /*
            HttpServletRequest에서 세션 받아오기
            RequestContextHolder가 현재 요청의 내용을 가지고 있는 클래스인데,
            여기서 currentRequestAttributes() 메소드를 호출하여 현재 요청의 속성을 받아오고,
            이것을 ServletRequestAttribuest로 형변환하여 getRequest() 메소드를 호출하면,
            HttpServletRequest를 받아올 수 있다.
	    */
        HttpSession session = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getSession();        /*
            우선 순위 때문에 의존성 주입이 되지 않아서 직접 설정에서 빈을 꺼내서 초기화
        */
        if(userService==null){
            WebApplicationContext context =
                    WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
            userService = context.getBean(UserService.class);
        }

        return userService.findByEmail((String)session.getAttribute("userSession"));
    }
}
