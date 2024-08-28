package pack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

import jakarta.servlet.http.HttpSessionEvent;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	// 애플리케이션 보안 설정을 담당
	// 특정 URL 허용 여부, 로그인 및 로그아웃 처리 방법, 사용자 인증 방법 등을 포함
	// 보안 설정, 인증 방법
	
	// 인증 처리 중 가장 중요한 클래스 : AuthenticationManager
	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration authConf) throws Exception{// 예외는 던져~
		return authConf.getAuthenticationManager();
	}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.authorizeHttpRequests(auth -> auth		// 요청에 대한 인증 및 권한 부여를 설정하는 곳
				.requestMatchers("/auth/login", "/auth/logout", "/static/**").permitAll() 	// 허용할 녀석들만 적어줘~ static폴더는 다 허용할거야
				.anyRequest().authenticated())	// 그 외 나머지는 인증을 해야해~
		.formLogin(formLogin -> formLogin	// 폼 기반 로그인 설정
				.loginPage("/auth/login")	// 로그인 경로를 지정
				.loginProcessingUrl("/auth/login")
				.usernameParameter("userid")
				.passwordParameter("name")
				.defaultSuccessUrl("/auth/success", true)
				.permitAll())
		.logout(logout -> logout
				.logoutUrl("/auth/logout")
				.logoutSuccessUrl("/auth/login")
				.invalidateHttpSession(true) 	// 세션 무효화
				.clearAuthentication(true)		// 인증 정보 제거
				.deleteCookies("JSESSIONID") 	// 세션 쿠키 삭제
				.permitAll());
		
		return http.build();
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean	// 세션 유효시간. 다른 방법으로 application.properties에서 주는 것도 가능
	public HttpSessionEventPublisher httpSessionEventPublisher() {
		return new HttpSessionEventPublisher() {
			@Override
			public void sessionCreated(HttpSessionEvent event) {
				event.getSession().setMaxInactiveInterval(600); // 600초 : 10분간 유효
				super.sessionCreated(event);
			}
		};
	}
}
