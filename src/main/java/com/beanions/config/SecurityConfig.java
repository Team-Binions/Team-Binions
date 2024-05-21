package com.beanions.config;


import com.beanions.auth.dto.MemberRole;
import com.beanions.config.handler.AuthSuccessHandler;
import com.beanions.config.handler.AuthFailHandler;
import com.beanions.config.handler.LogoutHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@Configuration
@EnableWebSecurity
@EnableRedisHttpSession
public class SecurityConfig {

    @Autowired
    private AuthFailHandler authFailHandler;
    @Autowired
    private AuthSuccessHandler authSuccessHandler;

    /* 비밀번호 암호화에 사용할 객체 BCryptPasswordEncoder bean 등록 */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /* 정적 리소스에 대한 요청은 제외하는 설정 */
    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return web -> web.ignoring()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations());
    }
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

//    private LogoutSuccessHandler logoutSuccessHandler() {
//        return new LogoutHandler();
//    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {

        /* 요청에 대한 권한 체크 */
        http.authorizeHttpRequests( auth -> {

            // 로그인 하지 않아도 요청가능한 url요청 목록(get/post)
            auth.requestMatchers(
                    "/request-dupCheck-id",
                    "/request-dupCheck-nickname",
                    "/request-verify-mail",
                    "/request-verify-wedd",
                    "/request-join-member",
                    "/login",
                    "/fail",
                    "/forgetInfo",
                    "/request-forget-verify-mail",
                    "/request-checkValid-mail",
                    "/request-checkValid-id-and-email",
                    "/",
                    "/main",
                    "/signup",
                    "/inquiry",
                    "/about",
                    "/privacy",
                    "/terms",
                    "/search",
                    "/board/review",
                    "/board/reviewdetail",
                    "/board/notice",
                    "/board/noticedetail",
                    "/board/magazine",
                    "/board/magazinedetail",
                    "/board/yerang",
                    "/board/yesin",
                    "/board/freedetail",
                    "/board/comments"
                    ).permitAll();
            
            // UserRole에 설정해준 상수 값과 비교해 접근 권한 부여
            auth.requestMatchers("/user/*").hasAnyAuthority(MemberRole.USER.getRole());
            auth.requestMatchers("/board/*").hasAnyAuthority(MemberRole.USER.getRole());
            auth.requestMatchers("/admin").hasAnyAuthority(MemberRole.ADMIN.getRole());
            auth.requestMatchers("/admin/*").hasAnyAuthority(MemberRole.ADMIN.getRole());
            auth.anyRequest().authenticated();

            // 해당 URL 요청과 그 값에 맞으면 로그인 시켜줌
        }).formLogin( login -> {
            login.loginPage("/login");
            login.usernameParameter("user");
            login.passwordParameter("pass");
            login.successHandler(authSuccessHandler);
            login.failureHandler(authFailHandler);

            // 해당 URL이 요청되면 해당 메서드 실행 (쿠키 지우기, 세션 전부 만료시키기 등)
        }).logout( logout -> {
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/auth/logout"));
            logout.deleteCookies("JSESSIONID");
            logout.invalidateHttpSession(true);
            logout.logoutSuccessUrl("/"); // 로그아웃 성공한 뒤 이동할 페이지 설정

        }).requestCache( cache -> cache
            .requestCache(new HttpSessionRequestCache())
            // 요청에 대한 URL을 보관하는 방법
         ).sessionManagement(session -> { session
            .maximumSessions(1)
            .maxSessionsPreventsLogin(false)
            .expiredUrl("/")
            .sessionRegistry(sessionRegistry());

            //CSRF 보안 공격 방어. 403에러 원인 범인1
        }).csrf( csrf -> csrf.disable());

        return http.build();
    }
}