package pack;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Service
public class JwtService {
	private Key key;	// JWT 토큰을 서명, 검증하는데 사용할 비밀키 저장용 변수
	
	@PostConstruct	// 객체 생성 후 의존성 주입이 완료되면 자동 호출         생성자 이후에 자동으로 발생되죠잉
	public void init() {
		key = Keys.secretKeyFor(SignatureAlgorithm.HS256);		// HS256 알고리즘을 사용해 비밀키 생성
	}
	
	public String createToken(String id) {
		return Jwts.builder()
				.setSubject(id)				// 토큰 주제를 사용자 id고 설정
				.setIssuedAt(new Date())	// 토큰 발행 시간
				.setExpiration(new Date(System.currentTimeMillis() + 36000000))// 토킅 유효시간(1시간))
				.signWith(key)	// 비밀키로 토큰에 서명
				.compact();	// JWT 문자열을 생성한 후 반환
	}
	
	// JWT 토근에서 사용한 id를 추출하기
	public String getUserFromToken(String token) {
		return Jwts.parserBuilder()
				.setSigningKey(key)	 //	 토큰을 검증할 때 사용할 비밀 키를 생성
				.build()	// 파서 빌더를 완성
				.parseClaimsJws(token)		// 주어진 JWT 토큰을 파싱하고 검증
				.getBody()		// 토큰 클레임을 가져옴
				.getSubject();		// 토큰 주제를 반환(토큰 생성 시 사용자 ID로 설정한 값, setSubject(id))
	}
}
