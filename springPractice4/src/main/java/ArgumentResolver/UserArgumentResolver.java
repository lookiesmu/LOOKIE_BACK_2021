package ArgumentResolver;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import service.UserService;
import vo.UserVO;

import javax.servlet.http.HttpSession;

public class UserArgumentResolver implements HandlerMethodArgumentResolver {
    private UserService userService;

    //true일 때만 resolveArgument 호출됨
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterType().equals(UserVO.class);
    }


    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();

        if(userService==null){
            WebApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(session.getServletContext());
            userService = context.getBean(UserService.class);
        }

        //세션에서 email 얻어와서 user 반환
        return userService.findByEmail((String)session.getAttribute("userSession"));
    }


}
