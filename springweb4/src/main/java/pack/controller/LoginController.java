package pack.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
	// 로그 정보 출력용 클래스
	private final Logger logger = LoggerFactory.getLogger(this.getClass());	// 현재 클래스에 대한 로그 출력
	
	@GetMapping("login")
	public String submitCall() {
		//return "login.html";	// forward : 기본값	타임리프 쓰면 templates로 가. jsp 쓰면 WEB-INF/views 로 가!
		
		return "redirect:login.html";	// redirect 명시적으로 적어줘야만 한다.    
		//return "redirect:http://localhost/login.html";
	}
	
	// 클라이언트가 전달한 값 수신 방법 1 : 전통적
	/*
	@PostMapping("login")
	public String submit(HttpServletRequest request, Model model) {	// id랑 비밀번호가 넘어오잖아! 받아줘야지!! 그래서 request, jsp로 내보낼거야 그래서 model
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		//System.out.println(id + " " + pwd);	// 초보자가 찍는 방법
		logger.info(id + " " + pwd); 	// 로그레벨 trace > debug > info > warn > error > fatal
		
		String data = "";
		if(id.equals("kor") && pwd.equals("1234"))
			data = "로그인 성공";
		else
			data = "로그인 실패";
		
		model.addAttribute("data", data);
		return "result";	// views에 들어있는 result.jsp를 부르는거야 => 포워딩
	}
	*/
	
	// 클라이언트가 전달한 값 수신 방법 2 : Spring 어노테이션 사용
	@PostMapping("login")
	public String submit(@RequestParam(value = "id")String id,
						//@RequestParam(value = "pwd")String pwd, 
						//@RequestParam(value = "pwd")int pwd, // 어노테이션은 이렇게 형변환을 해줄 수 있다
						@RequestParam(value = "pwd", defaultValue = "111")int pwd,	// 초기치를 줄 수도 있다
						Model model) {	// login.html에 있는 id랑 pwd야!! 클라이언트가 넘기는 모ㅗㅇ오오오든 데이터는 String이야
		
		String data = "";
		//if(id.equals("kor") && pwd.equals("1234"))
		if(id.equals("kor") && pwd == 111)
			data = "로그인 성공";
		else
			data = "로그인 실패";
		
		model.addAttribute("data", data);
		return "result";
	}
}