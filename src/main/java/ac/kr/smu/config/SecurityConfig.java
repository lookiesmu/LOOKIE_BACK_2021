package ac.kr.smu.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity //security 자동설정 허용
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)//Security 관련 Annotation 허용
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        form으로 로그인 수행. '/'로 요청, 처리는 /login으로. 성공하면 /board로 이동
        로그아웃은 성공하면 세션과 쿠키를 모두 지움
        요청은 인증 되어 있어야 함. 아래 설정한 경로는 모든 요청 허용. 나머지 요청은 모두 인증되어 있어야 함
        csrf 토큰 비활성화 (csrf 공격을 막기 위해 서버가 발행하는 토큰 으로, 이를 활성화 하면 모든 POST 요청에 토큰을 포함해야 함)
        */
        http.httpBasic().and().formLogin().loginPage("/").loginProcessingUrl("/login").defaultSuccessUrl("/board",true)
                .and().logout().deleteCookies("JSESSIONID").invalidateHttpSession(true)
                .and().authorizeRequests()
                .antMatchers("/vendor/**","/css/**","/js/**","/scss/**","/img/**","/","/user").permitAll()
                .anyRequest().authenticated().and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        /*
            테스트를 위해 username user와 비밀번호 1111 권한은 USER권한을 가진 user를 만듭니다.
            {noop}은 비밀번호를 인코딩하지 않았다는 의미입니다.
            Spring Security에서 설정을 하지 않는다면 우리가 표현하는 id는 username이고 비밀번호는 password입니다.
        */
        auth.inMemoryAuthentication().withUser("user").password("{noop}1111").roles("USER");
    }
}