package pack.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import pack.entity.UserEntity; // User 두 개여서 이름 + Entity로 바꿈


@Service
public class CustomUserDetailsService implements UserDetailsService {
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		System.out.println("username: " + username);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String role = "";
		if (username.equals("guest")) role = "ROLE_USER"; // 실제 DB에는 ROLE_XXX 형식으로 저장되어 있어야 함
		else if (username.equals("staff")) role = "ROLE_STAFF";
		else if (username.equals("admin")) role = "ROLE_ADMIN";
		
		// 원래는 DB에서 username을 이용해 사용자 정보(이름, 비밀번호, 이메일, 권한, ...)를 얻어옴
		UserEntity user = UserEntity.builder()
							.id(1).userName(username)	// db에서 pk값 써주면 돼ㅐ
							.userPass(encoder.encode("0000")).email("@").role(role).build();
		
		// 권한 정보도 따로 테이블을 만들어서 관리해 하나의 계정이 다양한 권한을 가질 수 있도록 해야 하나, 샘플이므로 생략
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(user.getRole()));
		
		UserDetails userDetails = new User(user.getUserName(), user.getUserPass(), authorities);
		return userDetails;
	}
}
