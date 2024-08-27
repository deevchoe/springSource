package pack;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {	// 기본적인 웹 보안 구성을 설정
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		// HttpSecurity 객체를 사용하여 보안 설정을 정의
		http
			.authorizeHttpRequests(authorizeRequest -> 	// http 요청에 대한 보안 권한 설정 부분
				authorizeRequest
					.requestMatchers("/login").permitAll() // antMatcher와 같은거야     login 경로는 인증없이 누구든 접근 허용
					.anyRequest().authenticated()	// 그 외 나머지 요청은 인증된 경우에 접근 허용
			)
			.formLogin(formLogin -> 
				formLogin
					.loginPage("/login") 		// login 페이지 경로 지정.      너네가 제공하는 거 말고 내가 원하는 로그인 페이지 쓸래~
					.defaultSuccessUrl("/", true)	// 로그인 성공 시 컨텍스트 루트"/"로 이동.
					.permitAll()	// login 페이지는 인증없이 누구든 접근 허용
			)
			.logout(logout -> 
				logout
					.logoutUrl("/logout")
					.logoutSuccessUrl("/login?logout") 	// 로그아웃 성공하면 로그인 페이지로 이동.  로그아웃 메세지를 뿌리기 위해 파라미터 logout을 달아줬다.
					.permitAll()		// logout은 인증없이 누구든 접근 허용
			)
			.sessionManagement(sessionManagement ->
					sessionManagement
						.maximumSessions(1)		// 최대 동시 세션 수를 제한
						.expiredUrl("/login?expired") // 세션 만료 시 로그인으로 이동.     파라미터가 expired면 세션이 만료됬습니다~ 이런 메세지 보낼 수 있는거야ㅏ
						
			);
		
		return http.build();
	}
	
	// 사용자가 입력한 아이디와 비밀번호를 리턴..?
	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder()
							.username("user")
							.password(passwordEncoder().encode("0000"))
							.roles("USER") 		// default user 역할
							.build();	// 사용자명과 비밀번호 역할 설정
		
		return new InMemoryUserDetailsManager(user);
		// 사용자 정보를 메모리에 저장하고 관리하는 클래스
		// 주로 어플리케이션, 테스트 환경에서 사용. 
		// 영구 저장소 X! 어플리케이션을 재시작하면 정보가 사라짐.
	}
	
	// 비밀번호는 암호화 해줘야해!!
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();		// 비밀번호 암호화를 위해 BCrypt(단방향 암호화) 알고리즘 사용
		// 단방향 해시함수를 이용하여 암호화 수행
	}

}
