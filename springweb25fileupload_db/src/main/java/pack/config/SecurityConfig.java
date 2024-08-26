package pack.config;

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

@Configuration	// 스프링의 설정을 위한 클래스.       환경 잡을때 써주는 클래스라는 소리. 뭔가를 설정하려고 만든 클래스야~ 자동으로 실행돼.
@EnableWebSecurity	// Spring Security 기능을 활성화
// 보안설정 자동화, 인증 및 권한 부여, 비밀번호 암호화, CSRF 보화, 기본 로그인 폼 제공, user라는 기본 사용자 계정 생성...
public class SecurityConfig {
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http
		.authorizeHttpRequests((requests) -> 
			requests.requestMatchers("/").permitAll()
			.anyRequest().authenticated()
		)// 루트경로는 모두 허용. 그 외는 인증 필요               로그인 없이 접근 가능한걸 결정 지어주는..?  로그인 없이 접근 할 수 있는 경로.  루트 경로는 아무나 접근할 수 있어~
		.formLogin((form) -> 
			form.loginPage("/login")
			.permitAll()	// 커스텀 로그인 페이지 설정         로그인 페이지는 모두 접근할 수 있어~
			)
		.logout((logout) -> 	// 로그아웃 설정을 정의
			logout.logoutUrl("/logout")
			.logoutSuccessUrl("/")
			.invalidateHttpSession(true)
			.deleteCookies("JSESSIONID")
			.permitAll()
		).csrf((csrf) -> 
			csrf.disable());	// csrf 보호는 테스트이므로 비활성화 배포할때는 활성화 시켜줘야해
		
		return http.build();
	}
	
	@Bean
	// 사용자 세부 정보를 관리하는 서비스 정의
	public UserDetailsService userDetailsService() {
		// 사용자 정보를 빌드
		UserDetails user = 
				User.builder()
				.username("user")
				.password(passwordEncoder().encode("p@ssw0rd"))
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
		// 메모리 내 사용자 세부 정보 관리자를 생성 후 반환
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// BCrypt는 암호화 해시 함수로 현재 사용 중인 강력한 해시 메커니즘 중 하나
		// 해시 : 입력 데이터를 고정된 길이의 고윳값(해시값)으로 변환하는 과정 또는 결과
		return new BCryptPasswordEncoder();
	}
}
