package ac.kr.smu.config;

import ac.kr.smu.ArgumentResolver.UserArgumentResolver;
import ac.kr.smu.Interceptor.LoginInterceptor;
import ac.kr.smu.Interceptor.PostInterceptor;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.*;


import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@EnableWebMvc
@Configuration
@ComponentScan("ac.kr.smu.controller")
public class ServletConfig  implements WebMvcConfigurer {
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        WebMvcConfigurer.super.configureViewResolvers(registry);
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        WebMvcConfigurer.super.addResourceHandlers(registry);
        registry.addResourceHandler("/vendor/**").addResourceLocations("/resources/vendor/");
        registry.addResourceHandler("/css/**").addResourceLocations("/resources/css/");
        registry.addResourceHandler("/scss/**").addResourceLocations("/resources/scss/");
        registry.addResourceHandler("/img/**").addResourceLocations("/resources/img/");
        registry.addResourceHandler("/js/**").addResourceLocations("/resources/js/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/board").addPathPatterns("/post/**");
        registry.addInterceptor(new PostInterceptor()).addPathPatterns("/post/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(new UserArgumentResolver());
    }
}
