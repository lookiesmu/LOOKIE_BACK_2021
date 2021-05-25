package ac.kr.smu.config;

import ac.kr.smu.jwt.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity //security 자동설정 허용
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)//Security 관련 Annotation 허용
@ComponentScan("ac.kr.smu.service")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        form으로 로그인 수행. '/'로 요청, 처리는 /login으로. 성공하면 /board로 이동
        로그아웃은 성공하면 세션과 쿠키를 모두 지움
        요청은 인증 되어 있어야 함. 아래 설정한 경로는 모든 요청 허용. 나머지 요청은 모두 인증되어 있어야 함
        csrf 토큰 비활성화 (csrf 공격을 막기 위해 서버가 발행하는 토큰 으로, 이를 활성화 하면 모든 POST 요청에 토큰을 포함해야 함)
        */
//        http.httpBasic().and().formLogin().loginPage("/").loginProcessingUrl("/login").defaultSuccessUrl("/board",true)
//                .and().logout().deleteCookies("JSESSIONID").invalidateHttpSession(true)
//                .and().authorizeRequests()
//                .antMatchers("/vendor/**","/css/**","/js/**","/scss/**","/img/**","/","/user").permitAll()
//                .anyRequest().authenticated().and().csrf().disable();
        http.httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().authorizeRequests()
                .antMatchers("/vendor/**","/css/**","/js/**","/scss/**","/img/**","/","/user").permitAll()
                .anyRequest().authenticated().and().csrf().disable()
                .addFilterBefore(new JWTAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

}