package JWT;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


@Component
@RequiredArgsConstructor
public class JWTAuthenticationFilter extends GenericFilterBean {
    private JWTTokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if(tokenProvider==null){
            tokenProvider = WebApplicationContextUtils.getWebApplicationContext(servletRequest.getServletContext())
                    .getBean(JWTTokenProvider.class);
        }

        String token = tokenProvider.resolveToken((HttpServletRequest) servletRequest);

        if(token != null && tokenProvider.validateToken(token)){
            Authentication authentication = tokenProvider.getAuthentication(token);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
