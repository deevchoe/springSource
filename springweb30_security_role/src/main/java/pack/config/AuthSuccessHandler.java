/*
👨🏻💬 https://cafe.daum.net/flowlife/HrhB/96

SecurityConfig 클래스에서 http.formLogin().successHandler(new CustomAuthenticationHandler())로 설정
*/
package pack.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class AuthSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	private RequestCache requestCache = new HttpSessionRequestCache();

	public AuthSuccessHandler() {
		super.setRequestCache(requestCache);
	}

	// 로그인 성공 이후 자동으로 호출되는 메소드
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		// 로깅, 세션, 예외 처리 등의 추가로직 구현
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60 * 20); // 세션 유지 시간 설정 (초 단위)
		SavedRequest cashed = requestCache.getRequest(request, response); // 로그인 성공 이후 미리 저장된 요청이 있었는지 읽어옴
		if (cashed == null) { // 로그인하지 않은 상태로 인증이 필요한 경로를 요청하지 않았다면 = 미리 저장된 요청이 없다면
			RequestDispatcher rd = request.getRequestDispatcher("/user/login_success");
			rd.forward(request, response); // 로그인 환영 페이지로 forward 이동
		} else { // 원래 가려던 목적지 경로로 redirect 시킴 (GET 방식 요청 파라미터도 자동으로 같이 가지고 감)
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}
}