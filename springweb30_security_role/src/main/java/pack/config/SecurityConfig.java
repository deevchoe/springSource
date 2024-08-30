package pack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	// 클라이언트 요청 -> 필터 + 필터 ... + DispatcherServlet 👨🏻💬https://cafe.daum.net/flowlife/HrhB/96

	@Bean
	SecurityFilterChain filterChain(HttpSecurity security) throws Exception{
		
		// 인증 없이 접근 가능 요청 URL
		String[] whiteList = {"/", "/notice", "/user/loginform", "/user/login_fail", "/user/expired", "/shop"};
		
		security
			.csrf(csrf -> csrf
					.disable()) // CSRF 처리 설정 해제
			.authorizeHttpRequests(config -> config // 사용자 인증 설정
					.requestMatchers(whiteList).permitAll()
					.requestMatchers("/admin/**").hasRole("ADMIN") // 특정 권한을 가진 사용자만 접근 허용
					.requestMatchers("/staff/**").hasAnyRole("ADMIN", "STAFF")
					.anyRequest().authenticated())
			.formLogin(config -> config
					.loginPage("/user/required_loginform")
					.loginProcessingUrl("/user/login") // 시큐리티가 자동으로 로그인 처리를 해줄 요청 경로 설정
					.usernameParameter("userName") // 이때 userName과 password를 알려야 함
					.passwordParameter("userPass")
					.successHandler(new AuthSuccessHandler()) // 핸들러를 등록하여 로그인 성공 이후 처리할 작업 수행
					.failureForwardUrl("/user/login_fail")
					.permitAll())
			.logout(config -> config
					.logoutUrl("/user/logout") // 시큐리티가 자동으로 로그아웃 처리를 해줄 경로 설정
					.logoutSuccessUrl("/") // 로그아웃 이후에 리다이렉트 경로 설정
					.permitAll())
			.exceptionHandling(config -> config // 인증 처리 중 예외 발생 시 설정. 권한 확인 과정에서 예외가 발생한 경우
					.accessDeniedPage("/user/denied")) // 시큐리티는 에러인 경우 403 Forbidden을 발생시키는데, 이때 이동할 경로를 설정
			.sessionManagement(config -> config
					.maximumSessions(1) // 최대 허용 세션 개수
					.expiredUrl("/user/expired")); // 허용되는 세션 개수를 초과하여 로그인이 해제된 경우 redirect할 경로 지정	
		
		return security.build();
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	AuthenticationManager authenticationManager(HttpSecurity httpSecurity, 
												UserDetailsService userDetailsService,
												BCryptPasswordEncoder bCryptPasswordEncoder) throws Exception{
		AuthenticationManagerBuilder authManagerBuilder = 
				httpSecurity.getSharedObject(AuthenticationManagerBuilder.class);
		authManagerBuilder
			.userDetailsService(userDetailsService)
			.passwordEncoder(bCryptPasswordEncoder);
		
		return authManagerBuilder.build();     
	}
}