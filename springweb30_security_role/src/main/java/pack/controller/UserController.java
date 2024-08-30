package pack.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class UserController {
	// 로그인하지 않은 상태로 로그인이 필요한 요청정보를 요청 시
	@GetMapping("/user/required_loginform")
	public String required_loginform() {
		return "user/required_loginform";
	}
	
	// 자발적으로 로그인 링크를 눌러 로그인 하는 경우
	@GetMapping("/user/loginform")
	public String loginform() {
		return "user/loginform";
	}
	
	// 로그인 성공 시 forward 되는 요청 경로
	@PostMapping("/user/login_success")
	public String login_success() {
		return "user/login_success";
	}
	
	// 로그인 폼을 제출(POST)한 로그인 프로세스 중 실패 시 forward
	@PostMapping("/user/login_fail")
	public String login_fail() {
		return "user/login_fail";
	}
	
	// 권한 부족 시
	@GetMapping("/user/denied")
	public String denied() {
		return "user/denied";
	}

	// ROLE_STAFF, ROLE_ADMIN 만 요청 가능
	@GetMapping("/staff/user/list")
	public String userlist() {
		return "user/list";
	}
	
	// ROLE_ADMIN 만 요청 가능
	@GetMapping("/admin/user/manage")
	public String userManage() {
		return "user/manage";
	}
	
	// 세션 사용 개수 초과 시
	@GetMapping("/user/expired")
	public String userExpired() {
		return "user/expired";
	}
}
