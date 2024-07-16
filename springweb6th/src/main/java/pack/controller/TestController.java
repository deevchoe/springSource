package pack.controller;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller	// 클라이언트의 요청을 받을거야ㅏ 그리고 뷰 파일을 클라이언트 브라우저로 보낼거야ㅏㅏㅏ
public class TestController {
	
	@GetMapping("thymeleaf")
	public String sijak(Model model) {
		model.addAttribute("msg", "타임리프 만세!");
		model.addAttribute("msg2", "🐧🐥🐧🐥");
		
		// DTO 자료 출력용
		Sangpum sangpum = new Sangpum();
		sangpum.setCode("10");
		sangpum.setSang("삼다수3L");
		sangpum.setPrice("3000");
		model.addAttribute("sangpum", sangpum);
		// 객체하나가리턴되기도
		ArrayList<Sangpum> list = new ArrayList<Sangpum>();
		list.add(sangpum);
		
		sangpum = new Sangpum();
		sangpum.setCode("20");
		sangpum.setSang("떡볶이");
		sangpum.setPrice("3000");
		list.add(sangpum);
		
		model.addAttribute("list", list); // 컬렉션이 리턴되기도
		
		return "list1";	// 서버에서 부르는거야
	}
}
