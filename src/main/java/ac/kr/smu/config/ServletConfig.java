package ac.kr.smu.config;

import ac.kr.smu.ArgumentResolver.UserArgumentResolver;
import ac.kr.smu.interceptor.LoginInterceptor;
import ac.kr.smu.interceptor.PostInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

// 어노테이션
@EnableWebMvc //WebMvc 관련 자동 설정
@Configuration // 설정관련 클래스라는 Annotation
@ComponentScan("ac.kr.smu.controller")
public class ServletConfig implements WebMvcConfigurer {
    /*
        뷰 관련 설정
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry){
        WebMvcConfigurer.super.configureViewResolvers(registry);
        registry.jsp("/WEB-INF/views/", ".jsp");
    }
    /*
        정적 리소스 관련 설정
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/vendor/**").addResourceLocations("/resources/vendor/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/scss/**").addResourceLocations("/resources/scss/");
        registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
        // webapp 디렉토리를 루트로하여 설정
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/board","/post/**","/*/file");
        registry.addInterceptor(new PostInterceptor()).addPathPatterns("/post/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(new UserArgumentResolver());
    }
    /*
        파일 요청 관련
     */
    @Bean
    public StandardServletMultipartResolver standardServletMultipartResolver(){
        return new StandardServletMultipartResolver();
    }
}
