package pack;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;

/*
@RestController	// @Controller + @ResponseBody    Restful, ajax 할 때 사용
public class TestController {
	@RequestMapping("test1") // 요청명. 어떤 요청이 들어오는데!!를 어노테이션으로 표현.  test1 요청이 들어오면 abc() 메소드를 수행해.  클라이언트 요청을 하면 web.xml로 가 디스패처 서블릿. 핸들러 매핑. 
	public String abc() {
		return " 요청에 대한 반응 보이기";
	}
}
*/

@Controller  // 사용자의 요청을 받아서 처리한 후 지정된 뷰(템플릿 엔진:jsp,밸로시티,타임리프 ...)에 모델(구구단을 하던. DB에 갖다오던. 이걸 jsp로 넘기는거) 객체를 넘겨주는 역할
public class TestController {
	@RequestMapping("test1")	// get, post 모두 처리
	public ModelAndView abc() {	//  ModelAndView를 리턴해
		//System.out.println("abc 처리");  // 모델엔뷰를 리턴해. 그럼 그걸 갖고 모델무무우...를 찾아가서 걔가 뷰를 찾아. 근데 뷰 파일이 없잖아! ㅇㅇ... 
		//return null;
		
		//return new ModelAndView("list", "msg", "안녕?jsp");	// list.jsp를 찾아 키는 msg value는 안녕?jsp        뷰파일명 : list / 모델 : "msg", "안녕?jsp"
		
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("list");
		// addObject는 request.setAttribute("msg", "안녕?jsp");이거랑 같다.
		// HttpServletRequest를 사용해 키, 값으로 jsp에 전송
		modelAndView.addObject("msg", "안녕?jsp");
		return modelAndView;
	}
	
	@RequestMapping(value="test2", method=RequestMethod.GET)	// get 요청 처리
	public ModelAndView abc2() {	
		return new ModelAndView("list", "msg", "성공2");
	}
	
	@GetMapping("test3")	// get 요청 처리.  PostMapping도 이쒀...
	public ModelAndView abc3() {
		return new ModelAndView("list", "msg", "성공3");
	}
	@GetMapping("test4")	// get 요청 처리
	public String abc4(Model model) {
		model.addAttribute("msg", "성공4");
		return "list";
	}
	
	//@RequestMapping(value="test5", method=RequestMethod.GET)	// get 요청 처리   요청 방식이 잘못돼서 에러 떨어짐
	@PostMapping("test5")	// post 요청 처리
	public ModelAndView abc5() {	
		return new ModelAndView("list", "msg", "성공5");
	}
	
	@PostMapping("test6")	// post 요청 처리
	public ModelAndView abc6() {
		return new ModelAndView("list", "msg", "성공6");
	}
	
	@PostMapping("test7")	// post 요청 처리
	public String abc7(Model model) {
		model.addAttribute("msg", "성공7");
		return "list";	// model이 있으면 뷰파일명.
	}
	
	@GetMapping("test8")
	@ResponseBody	// 그냥 데이터를 넘긴거야! jsp 파일을 넘긴게 아니야!
	public String abc8() {	// model이 없다. 진짜 String!
		String value = "일반 데이터-String, Map, JSON 등을 전달 가능";
		return value;
	}

	@GetMapping("test8_1")
	@ResponseBody
	public DataDto test8_1() {
		DataDto dto = new DataDto();
		dto.setCode(10);
		dto.setName("톰아저씨");
		return dto;		// JSON 형태로 반환 (참고:jackson 라이브러리가 지원) dto를 주면 json 형태로 반환해! 이걸 ajax가 받아서 처리하면 되겠네!
	}
}